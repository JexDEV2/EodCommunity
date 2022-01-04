package com.superdevelopment.eodcommunity.variables;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.data.configs.PlayerDataConfig;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.utils.StringArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class Clan {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();
    private PlayerDataConfig playerDataCfg = new PlayerDataConfig();

    private String joinMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerJoinClan"));

    private UUID id;
    private String name;
    private String description;
    private String tag;
    private int level;
    private int xp;
    private UUID leader;
    private List<UUID> elders;
    private List<UUID> officers;
    private List<UUID> members;
    private List<UUID> overallMembers;
    private List<Clan> allies;
    private List<Clan> enemies;

    public Clan(UUID id) {
        ClansConfig clansCfg = new ClansConfig();

        this.id = id;
        String clanId = String.valueOf(this.id);

        this.name = clansCfg.getClansCfg().getString(clanId + ".Name");
        this.description = clansCfg.getClansCfg().getString(clanId + ".Description");
        this.tag = clansCfg.getClansCfg().getString(clanId + ".Tag");
        this.level = clansCfg.getClansCfg().getInt(clanId + ".Level");
        this.xp = clansCfg.getClansCfg().getInt(clanId + ".Xp");
        this.leader = UUID.fromString(clansCfg.getClansCfg().getString(clanId + ".Leader"));
        this.elders = StringArrayList.reverseConvert((List<String>) clansCfg.getClansCfg().getList(clanId + ".Elders"));
        this.officers = StringArrayList.reverseConvert((List<String>) clansCfg.getClansCfg().getList(clanId + ".Officers"));
        this.members = StringArrayList.reverseConvert((List<String>) clansCfg.getClansCfg().getList(clanId + ".Members"));
        this.overallMembers = StringArrayList.reverseConvert((List<String>)clansCfg.getClansCfg().getList(clanId + ".OverallMembers"));
        this.allies = (List<Clan>) clansCfg.getClansCfg().getList(clanId + ".Allies");
        this.enemies = (List<Clan>) clansCfg.getClansCfg().getList(clanId + ".Enemies");
    }
    public Clan(Player player, String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = "No description set!";
        this.tag = "XXX";
        this.level = 1;
        this.xp = 0;
        this.leader = player.getUniqueId();
        this.elders = new ArrayList<>();
        this.officers = new ArrayList<>();
        this.members = new ArrayList<>();
        this.overallMembers = new ArrayList<>();
        this.allies = new ArrayList<>();
        this.enemies = new ArrayList<>();

        saveClan();
    }
    public void saveClan() {
        ClansConfig clansCfg = new ClansConfig();

        String clanId = String.valueOf(this.id);

        if(!clansCfg.getClansCfg().contains(clanId)) {
            clansCfg.getClansCfg().createSection(clanId);
        }

        clansCfg.getClansCfg().set(clanId + ".Name", this.name);
        clansCfg.getClansCfg().set(clanId + ".Description", this.description);
        clansCfg.getClansCfg().set(clanId + ".Tag", this.tag);
        clansCfg.getClansCfg().set(clanId + ".Level", this.level);
        clansCfg.getClansCfg().set(clanId + ".Xp", this.xp);
        clansCfg.getClansCfg().set(clanId + ".Leader", String.valueOf(this.leader));
        clansCfg.getClansCfg().set(clanId + ".Elders", StringArrayList.convert(this.elders));
        clansCfg.getClansCfg().set(clanId + ".Officers", StringArrayList.convert(this.officers));
        clansCfg.getClansCfg().set(clanId + ".Members", StringArrayList.convert(this.members));
        clansCfg.getClansCfg().set(clanId + ".OverallMembers", StringArrayList.convert(this.overallMembers));
        clansCfg.getClansCfg().set(clanId + ".Allies", this.allies);
        clansCfg.getClansCfg().set(clanId + ".Enemies", this.enemies);

        clansCfg.saveClansFile();
    }
    public void addPlayer(Player player, boolean message, ClanRank rank) {
        UUID playerUuid = player.getUniqueId();

        this.overallMembers.add(playerUuid);

        playerDataCfg.getPlayerDataCfg().set(playerUuid + ".Clan", this.id.toString());

        if(rank.equals(ClanRank.LEADER)) {
            this.leader = playerUuid;
            playerDataCfg.getPlayerDataCfg().set(playerUuid + ".ClanRank", ClanRank.LEADER.toString());
        } else if(rank.equals(ClanRank.ELDER)) {
            this.elders.add(playerUuid);
            playerDataCfg.getPlayerDataCfg().set(playerUuid + ".ClanRank", ClanRank.ELDER.toString());
        } else if(rank.equals(ClanRank.OFFICER)) {
            this.officers.add(playerUuid);
            playerDataCfg.getPlayerDataCfg().set(playerUuid + ".ClanRank", ClanRank.OFFICER.toString());
        } else if(rank.equals(ClanRank.MEMBER)) {
            this.members.add(playerUuid);
            playerDataCfg.getPlayerDataCfg().set(playerUuid + ".ClanRank", ClanRank.MEMBER.toString());
        }
        playerDataCfg.savePlayerDataFile();
        saveClan();

        if(message) {
            for(UUID uuid : overallMembers) {
                OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(uuid);
                if(oPlayer instanceof Player) {
                    ((Player) oPlayer).sendMessage(joinMessage.replace("%player_name%", player.getName()));
                }
            }
        }
    }
    public void removePlayer(OfflinePlayer player) {
        UUID playerUuid = player.getUniqueId();

        this.overallMembers.remove(playerUuid);

        playerDataCfg.getPlayerDataCfg().set(playerUuid + ".Clan", "none");
        playerDataCfg.getPlayerDataCfg().set(playerUuid + ".ClanRank", "none");
    }
    public void disband() {
        broadcastMessage(Messages.clanDisbanded.replace("%clan_name%", this.name));

        ClansConfig clansCfg = new ClansConfig();
        PlayerDataConfig playerDataCfg = new PlayerDataConfig();
        
        for(UUID uuid : this.overallMembers) {
            playerDataCfg.getPlayerDataCfg().set(uuid + ".Clan", "none");
            playerDataCfg.getPlayerDataCfg().set(uuid + ".ClanRank", "none");
        }
        playerDataCfg.savePlayerDataFile();
        
        for(Clan clan : this.enemies) {
            List<String> tEnemies = clansCfg.getClansCfg().getStringList(clan.getId() + ".Enemies");
            tEnemies.remove(this.id);
            clansCfg.getClansCfg().set(clan.getId() + ".Enemies", tEnemies);
        }
        for(Clan clan : this.allies) {
            List<String> tAllies = clansCfg.getClansCfg().getStringList(clan.getId() + ".Allies");
            tAllies.remove(this.id);
            clansCfg.getClansCfg().set(clan.getId() + ".Allies", tAllies);
        }
        clansCfg.getClansCfg().set(this.id.toString(), null);

        clansCfg.saveClansFile();
    }
    public void promotePlayer(OfflinePlayer player) {
        ClanRank rank = ClanRank.getPlayerRank(player);
        ClanRank newRank = rank.getPromotionRank();

        if(newRank == ClanRank.ELDER) {
            this.officers.remove(player.getUniqueId());
            this.elders.add(player.getUniqueId());
        } else if(newRank == ClanRank.OFFICER) {
            this.members.remove(player.getUniqueId());
            this.officers.add(player.getUniqueId());
        }
        saveClan();

        playerDataCfg.getPlayerDataCfg().set(player.getUniqueId() + ".ClanRank", newRank.toString());
        playerDataCfg.savePlayerDataFile();
    }
    public void demotePlayer(OfflinePlayer player) {
        ClanRank rank = ClanRank.getPlayerRank(player);
        ClanRank newRank = rank.getDemotionRank();

        if(newRank == ClanRank.MEMBER) {
            this.officers.remove(player.getUniqueId());
            this.members.add(player.getUniqueId());
        } else if(newRank == ClanRank.OFFICER) {
            this.elders.remove(player.getUniqueId());
            this.officers.remove(player.getUniqueId());
        }

        saveClan();

        playerDataCfg.getPlayerDataCfg().set(player.getUniqueId() + ".ClanRank", newRank.toString());
        playerDataCfg.savePlayerDataFile();
    }
    
    public void removeEnemy(Clan clan) {
        ClansConfig clansCfg = new ClansConfig();
        List<String> enemies = clansCfg.getClansCfg().getStringList(clan.getId() + ".Enemies");
        enemies.remove(this.id);
    }

    public void broadcastMessage(String message) {
        final List<UUID> currentOverallPlayers = this.overallMembers;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                for (UUID u : currentOverallPlayers) {
                    if (Bukkit.getOfflinePlayer(u) instanceof Player) {
                        Bukkit.getPlayer(u).sendMessage(message);
                    }
                }
            }
        });
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
        saveClan();
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }

    public UUID getLeader() {
        return leader;
    }
    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    public List<UUID> getElders() {
        return elders;
    }
    public void setElders(List<UUID> elders) {
        this.elders = elders;
    }

    public List<UUID> getOfficers() {
        return officers;
    }
    public void setOfficers(List<UUID> officers) {
        this.officers = officers;
    }

    public List<UUID> getMembers() {
        return members;
    }
    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    public List<UUID> getOverallMembers() {
        return overallMembers;
    }
    public void setOverallMembers(List<UUID> overallMembers) {
        this.overallMembers = overallMembers;
    }

    public List<Clan> getAllies() {
        return allies;
    }
    public void setAllies(List<Clan> allies) {
        this.allies = allies;
    }

    public List<Clan> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<Clan> enemies) {
        this.enemies = enemies;
    }
}
