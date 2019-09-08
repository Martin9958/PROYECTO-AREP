package edu.escuelaing.arem.connection.reflection;

import javax.annotation.PostConstruct;

/**
 *
 */
public class Pokemon {

    /**
     *
     * @return
     */

    public String pokemonSum(String a, String b){
        return "The pokemon sum is: "+(Integer.parseInt(a) + Integer.parseInt(b));
    }

    public String pokemon(String name,
                          String nationality,
                          String type,
                          String gender,
                          String weight,
                          String height,
                          String attack,
                          String specialAttack,
                          String defense,
                          String specialDefense){

        return "The pokemon name is : "+ name + "\n" +
                "The pokemon nationality is : "+ nationality + "\n" +
                "The pokemon type is : "+ type + "\n" +
                "The pokemon gender is : "+ gender + "\n" +
                "The pokemon weight is : "+ weight + "\n" +
                "The pokemon weight is : "+ height + "\n" +
                "The pokemon attack is : "+ attack + "\n" +
                "The pokemon attack is : "+ specialAttack + "\n" +
                "The pokemon attack is : "+ defense + "\n" +
                "The pokemon attack is : "+ specialDefense + " ";
    }

    /**
     *
     * @param name
     * @return
     */
    public String pokemonName(String name) {
        String pokemonName = "The pokemon name is : "+ name + " ";
        return pokemonName;
    }

    /**
     *
     * @param nationality
     * @return
     */
    public String pokemonNationality(String nationality){
        String pokemonNationality = "The pokemon nationality is : "+ nationality + " ";
        return pokemonNationality;
    }

    /**
     *
     * @param type
     * @return
     */
    public String pokemonType(String type){
        String pokemonType = "The pokemon type is : "+ type + " ";
        return pokemonType;
    }

    /**
     *
     * @param gender
     * @return
     */
    public String pokemonGender(String gender){
        String pokemonGender = "The pokemon gender is : "+ gender + " ";
        return pokemonGender;

    }

    /**
     *
     * @param weight
     * @return
     */
    public String pokemonWeight(String weight){
        String pokemonWeight = "The pokemon weight is : "+ weight + " ";
        return pokemonWeight;
    }

    /**
     *
     * @param height
     * @return
     */
    public String pokemonHeight(String height){
        String pokemonHeight = "The pokemon weight is : "+ height + " ";
        return pokemonHeight;
    }

    /**
     *
     * @param attackMax
     * @return
     */
    public String pokemonAttack(String attackMax){
        String pokemonAttack = "The pokemon attack is : "+ attackMax + " ";
        return pokemonAttack;
    }

    /**
     *
     * @param specialAttack
     * @return
     */
    public String pokemonSpecialAttack(String specialAttack){
        String pokemonSpecialAttack = "The pokemon attack is : "+ specialAttack + " ";
        return pokemonSpecialAttack;
    }

    /**
     *
     * @param defense
     * @return
     */
    public String pokemonDefense(String defense){
        String pokemonDefense = "The pokemon attack is : "+ defense + " ";
        return pokemonDefense;
    }

    /**
     *
     * @param specialDefense
     * @return
     */
    public String pokemonSpecialDefense(String specialDefense){
        String pokemonSpecialDefense = "The pokemon attack is : "+ specialDefense + " ";
        return pokemonSpecialDefense;
    }

}
