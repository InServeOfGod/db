public abstract class BaseData {
    private String id;
    private String name;
    private String surname;
    private String mother_name;
    private String father_name;
    private String birth_date;
    private String mail_address;
    private String mail_password;
    private String pc_username;
    private String pc_password;
    private String pc_os;
    private String pc_mac;
    private String router_ESSID;
    private String router_BSSID;
    private String router_password;
    private String router_external_ip;
    private String router_PIN;
    private String router_admin_username;
    private String router_admin_password;
    private String location;
    private String details;
    private String[] columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPc_username() {
        return pc_username;
    }

    public void setPc_username(String pc_username) {
        this.pc_username = pc_username;
    }

    public String getPc_password() {
        return pc_password;
    }

    public void setPc_password(String pc_password) {
        this.pc_password = pc_password;
    }

    public String getPc_os() {
        return pc_os;
    }

    public void setPc_os(String pc_os) {
        this.pc_os = pc_os;
    }

    public String getPc_mac() {
        return pc_mac;
    }

    public void setPc_mac(String pc_mac) {
        this.pc_mac = pc_mac;
    }

    public String getRouter_ESSID() {
        return router_ESSID;
    }

    public void setRouter_ESSID(String router_ESSID) {
        this.router_ESSID = router_ESSID;
    }

    public String getRouter_password() {
        return router_password;
    }

    public void setRouter_password(String router_password) {
        this.router_password = router_password;
    }

    public String getRouter_BSSID() {
        return router_BSSID;
    }

    public void setRouter_BSSID(String router_BSSID) {
        this.router_BSSID = router_BSSID;
    }

    public String getRouter_external_ip() {
        return router_external_ip;
    }

    public void setRouter_external_ip(String router_external_ip) {
        this.router_external_ip = router_external_ip;
    }

    public String getRouter_PIN() {
        return router_PIN;
    }

    public void setRouter_PIN(String router_PIN) {
        this.router_PIN = router_PIN;
    }

    public String getRouter_admin_username() {
        return router_admin_username;
    }

    public void setRouter_admin_username(String router_admin_username) {
        this.router_admin_username = router_admin_username;
    }

    public String getRouter_admin_password() {
        return router_admin_password;
    }

    public void setRouter_admin_password(String router_admin_password) {
        this.router_admin_password = router_admin_password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public String getMail_password() {
        return mail_password;
    }

    public void setMail_password(String mail_password) {
        this.mail_password = mail_password;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns() {
        columns = new String[]{"ID", "NAME", "SURNAME", "MOTHER NAME", "FATHER NAME", "BIRTH DATE", "MAIL ADDRESS",
                "MAIL PASSWORD", "PC USERNAME", "PC PASSWORD", "PC OS", "PS MAC", "ROUTER ESSID", "ROUTER BSSID",
                "ROUTER PASSWORD", "ROUTER EXTERNAL IP", "ROUTER PIN", "ROUTER ADMIN USERNAME", "ROUTER ADMIN PASSWORD",
                "LOCATION", "DETAILS"
        };
    }

    public String[] getAll(){
        return new String[]{
                id,
                name,
                surname,
                mother_name,
                father_name,
                birth_date,
                mail_address,
                mail_password,
                pc_username,
                pc_password,
                pc_os,
                pc_mac,
                router_ESSID,
                router_BSSID,
                router_password,
                router_external_ip,
                router_PIN,
                router_admin_username,
                router_admin_password,
                location,
                details
        };
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
