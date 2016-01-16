package fr.ayoubdev.rnak.components.button;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.widget.Button;
import fr.ayoubdev.rnak.utils.api.Node;

/**
 * Created by Adib on 27/12/2015.
 */
public class ButtonComponent extends Button implements Node {
    public ButtonComponent(Context context) {
        super(context);

        this.setAllCaps(false);
    }

    public void _setBackgroundColor(String color) {
        //setBackgroundColor ne conserve pas le comportement "shaded" du bouton,
        //on doit utiliser un filtre de couleur pour conserver les effets:
        //this.setBackgroundColor(Color.argb(255,255,0,0));

        //Couleurs acceptées: #RRGGBB #AARRGGBB 'red', 'blue', 'green', 'black', 'white',
        //'gray', 'cyan', 'magenta', 'yellow', 'lightgray', 'darkgray':
        this.getBackground().setColorFilter(Color.parseColor(color), PorterDuff.Mode.MULTIPLY);

        return;
    }

    public void _setTextColor(String color) {
        this.setTextColor(Color.parseColor(color));

        return;
    }

    /*public void _setText(String text) {
        this.setText(text);

        return;
    }*/

    public void _setTextSize(int size) {
        //graduation sp au lieu de dip pour les fonts:
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

        return;
    }

    @Override
    public ButtonComponent create(Context context) {
        return new ButtonComponent(context);
    }
}
