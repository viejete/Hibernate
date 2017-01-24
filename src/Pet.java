
public class Pet {

    private boolean canBattle;
    private int id;
    private String name;
    private String family;
    private String icon;
    private String strongAgainst;
    private int typeId;
    private String weakAgainst;
    private int petQualityId;
    private int health;
    private int power;
    private int speed;


    public Pet() {}

    public Pet(boolean canBattle, String name, String family, String icon, String strongAgainst, int typeId, String weakAgainst, int petQualityId, int health, int power, int speed) {
        this.canBattle = canBattle;
        this.name = name;
        this.family = family;
        this.icon = icon;
        this.strongAgainst = strongAgainst;
        this.typeId = typeId;
        this.weakAgainst = weakAgainst;
        this.petQualityId = petQualityId;
        this.health = health;
        this.power = power;
        this.speed = speed;
    }

    public boolean isCanBattle() {
        return canBattle;
    }

    public void setCanBattle(boolean canBattle) {
        this.canBattle = canBattle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStrongAgainst() {
        return strongAgainst;
    }

    public void setStrongAgainst(String strongAgainst) {
        this.strongAgainst = strongAgainst;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getWeakAgainst() {
        return weakAgainst;
    }

    public void setWeakAgainst(String weakAgainst) {
        this.weakAgainst = weakAgainst;
    }

    public int getPetQualityId() {
        return petQualityId;
    }

    public void setPetQualityId(int petQualityId) {
        this.petQualityId = petQualityId;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        Pet obj2 = (Pet) obj;

        if((this.id == obj2.getId()) && (this.name.equals(obj2.getName())))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp;
        tmp = ( id + name ).hashCode();
        return tmp;
    }
}
