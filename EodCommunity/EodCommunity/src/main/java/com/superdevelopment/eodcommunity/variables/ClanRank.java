package com.superdevelopment.eodcommunity.variables;

import com.superdevelopment.eodcommunity.data.configs.PlayerDataConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public enum ClanRank {
    MEMBER, OFFICER, ELDER, LEADER;

    public int getId() {
        switch(this) {
            case MEMBER: return 1;
            case OFFICER: return 2;
            case ELDER: return 3;
            case LEADER: return 4;
        }
        return 0;
    }
    public String getDisplayName() {
        switch(this) {
            case MEMBER: return "Member";
            case OFFICER: return "Officer";
            case ELDER: return "Elder";
            case LEADER: return "Leader";
        }
        return null;
    }
    public ClanRank getRankFromId(int id) {
        switch(id) {
            case 1: return MEMBER;
            case 2: return OFFICER;
            case 3: return ELDER;
            case 4: return LEADER;
        }
        return null;
    }
    public boolean disbandPermission() {
        switch (this) {
            case MEMBER:
            case OFFICER:
            case ELDER:
                return false;
            case LEADER: return true;
        }
        return false;
    }
    public boolean invitePermission() {
        switch(this) {
            case MEMBER: return false;
            case OFFICER:
            case ELDER:
            case LEADER:
                return true;
        }
        return false;
    }
    public boolean promotePermission(ClanRank rank) {
        switch(this) {
            case MEMBER:
            case OFFICER:
                return false;
            case ELDER:
                if(rank == MEMBER) return true;
                else return false;
            case LEADER:
                if(rank == MEMBER || rank == OFFICER || rank == ELDER) return true;
                else return false;
        }
        return false;
    }
    public boolean kickPermission(ClanRank kickedPlayerRank) {
        switch(this) {
            case MEMBER: return false;
            case OFFICER:
                if(kickedPlayerRank == MEMBER) return true;
                else return false;
            case ELDER:
                if(kickedPlayerRank == MEMBER || kickedPlayerRank == OFFICER) return true;
                else return false;
            case LEADER:
                if(kickedPlayerRank == MEMBER || kickedPlayerRank == OFFICER || kickedPlayerRank == ELDER) return true;
                else return false;
        }
        return false;
    }
    public boolean descriptionPermission() {
        switch(this) {
            case MEMBER:
            case OFFICER:
            case ELDER:
                return false;
            case LEADER: return true;
        }
        return false;
    }
    public boolean tagPermission() {
        switch (this) {
            case MEMBER:
            case OFFICER:
            case ELDER:
                return false;
            case LEADER:
                return true;
        }
        return false;
    }
    public static ClanRank getPlayerRank(UUID uuid) {
        PlayerDataConfig playerDataCfg = new PlayerDataConfig();
        String rank = playerDataCfg.getPlayerDataCfg().getString(uuid + ".ClanRank");
        if(rank.equals("none")) {
            return null;
        }
        return ClanRank.valueOf(rank);
    }
    public static ClanRank getPlayerRank(OfflinePlayer player) {
        PlayerDataConfig playerDataCfg = new PlayerDataConfig();
        String rank = playerDataCfg.getPlayerDataCfg().getString(player.getUniqueId() + ".ClanRank");
        if(rank.equals("none")) {
            return null;
        }
        return ClanRank.valueOf(rank);
    }
    public ClanRank getPromotionRank() {
        switch(this) {
            case MEMBER: return OFFICER;
            case OFFICER: return ELDER;
            case ELDER: return LEADER;
            case LEADER: return null;
        }
        return null;
    }
    public ClanRank getDemotionRank() {
        switch(this) {
            case MEMBER: return null;
            case OFFICER: return MEMBER;
            case ELDER: return OFFICER;
            case LEADER: return ELDER;
        }
        return null;
    }
}
