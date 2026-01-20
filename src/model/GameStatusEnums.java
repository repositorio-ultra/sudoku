package model;

public enum GameStatusEnums {

    NON_STARTED("não iniciado"),
    INCOMPLETE("incompleto"),
    COMPLETE("completo");

    private String label;

    GameStatusEnums(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
