package intellisoft.bo.com.intellibusiness.listeners;

import intellisoft.bo.com.intellibusiness.entity.adm.Usuario;

/**
 * Created by Subieta on 30/03/2016.
 */
public interface OnCompleteLogin {

    void onCorrectLoggin(Usuario usuario);
    void onIncorrectLoggin();
    void onCancelLoggin();
}
