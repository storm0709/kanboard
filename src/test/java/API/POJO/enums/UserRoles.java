package API.POJO.enums;

public enum UserRoles {
    ADMIN("app-admin"),
    MANAGER("app-manager"),
    USER("app-user");
    private String role;

    UserRoles(String role){
        this.role=role;
    }

    public String getRole(){
        return role;
    }
}
