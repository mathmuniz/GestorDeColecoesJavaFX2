/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author mmuni
 */
public class DaoFactory {

    public static SkinDaoJdbc novaSkinDao() throws Exception {
        return new SkinDaoJdbc();
    }
    
}
