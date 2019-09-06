package edu.escuelaing.arem.connection.reflection;

/**
 *
 */
public class Pokemon {

    /**
     *
     * @param name
     * @param nationality
     * @param kind
     * @param type
     * @param generation
     * @return
     */
    public String pokemon(String name, String nationality, String kind, String type, String generation) {
        String pokemonData = "name: "+ name
                + "nationality: " + nationality
                + "kind: " + kind
                + "type: " + type
                + "generation: " + generation;
       return pokemonData;
    }

    /**
     *
     * @param attackMax
     * @return
     */
    public Double attack(Integer attackMax){
        return attackMax*0.5;
    }

    /**
     *
     * @param pk1
     * @param pk2
     * @return
     */
    public String versus(Integer pk1, Integer pk2){
        String winner = "";
        if((pk2-pk1) < 0){
            winner = "winner is pk1";
        }
        else{
            winner = "winner is pk2";
        }
        return winner;
    }

}
