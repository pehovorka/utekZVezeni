/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.pehovorka.utekZVezeni.main;

import com.github.pehovorka.utekZVezeni.logika.*;
import com.github.pehovorka.utekZVezeni.uiText.TextoveRozhrani;
import java.io.File;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public final class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        if (args.length == 0){
            ui.hraj();

        }
        else{
            ui.hrajZeSouboru(new File(args[0]));
        }
    }

    private Start(){};
}
