package model;


import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    // a lista externa representa a coluna e a interna a linha
    public final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnums getStatus(){
        // para pegar o Stream da lISTA INTERNA, UMA VEZ QUE É UM ARRAY DE ARRAY USAMOS O COLLECTION::STREAM
        if(spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getAtual()))){
            return GameStatusEnums.NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getAtual())) ? GameStatusEnums.INCOMPLETE : GameStatusEnums.COMPLETE;
    }

    public boolean hasErrors(){

        if (getStatus().equals(GameStatusEnums.NON_STARTED)){
            return false;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> !s.getAtual().equals(s.getExpected()) && nonNull(s.getAtual()));
    }

    public boolean changeValue( final int col, final int row, Integer value){
        var space = spaces.get(col).get(row);
        if(space.isFixed()){
            return false;
        }
        space.setAtual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if(space.isFixed()){
            return false;
        }
        space.clearSpace();
        return true;
    }

    public boolean reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
        return true;
    }

    public boolean gameIsFinished(){
        return !hasErrors() && getStatus().equals(GameStatusEnums.COMPLETE);
    }
}




