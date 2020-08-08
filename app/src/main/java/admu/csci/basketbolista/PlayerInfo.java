package admu.csci.basketbolista;

import android.media.Image;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PlayerInfo extends RealmObject {
    @PrimaryKey
    private String ownerid;
    private byte[] profilepicture;
//    private String profilepicture;
    private String name;
    private String hometown;
    private String team;
    private String age;
    private String height;
    private String weight;
    private String points;
    private String assists;
    private String rebounds;
    private String blocks;
    private String steals;
    private String wins;

    // generate > getters and setters
    public String getOwnerid() {
        return ownerid;
    }
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public byte[] getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(byte[] profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHometown() {
        return hometown;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public String getRebounds() {
        return rebounds;
    }

    public void setRebounds(String rebounds) {
        this.rebounds = rebounds;
    }

    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    public String getSteals() {
        return steals;
    }

    public void setSteals(String steals) {
        this.steals = steals;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }
// generate > toString

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "ownerid='" + ownerid + '\'' +
                ", name='" + name + '\'' +
                ", hometown='" + hometown + '\'' +
                ", team='" + team + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", points='" + points + '\'' +
                ", assists='" + assists + '\'' +
                ", rebounds='" + rebounds + '\'' +
                ", blocks='" + blocks + '\'' +
                ", steals='" + steals + '\'' +
                ", wins='" + wins + '\'' +
                '}';
    }
}