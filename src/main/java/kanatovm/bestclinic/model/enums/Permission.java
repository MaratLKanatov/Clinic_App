package kanatovm.bestclinic.model.enums;

public enum Permission {
    PATIENT_INDEX("ALLOW_PATIENT_INDEX"),
    DOCTOR_INDEX("ALLOW_DOCTOR_INDEX"),
    ADMIN_INDEX("ALLOW_ADMIN_INDEX");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
