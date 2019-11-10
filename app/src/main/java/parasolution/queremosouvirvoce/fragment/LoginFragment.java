package parasolution.queremosouvirvoce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import parasolution.queremosouvirvoce.R;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + "(?=.*[a-zA-Z])" + "(?=\\S+$)" + ".{6,}" + "$");

    View view;
    Context context;
    private TextInputLayout txtInputUsuario;
    private TextInputLayout txtInputSenha;
    private Button btnLogin;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
    }

    //metodo que monta a view (fragmento)
    //utilizando neste caso uma variavel global para q a view esteja visivel para toda esta classe
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_login, container, false);

        txtInputUsuario = view.findViewById(R.id.txtInputUsuario);
        txtInputSenha = view.findViewById(R.id.txtInputSenha);
        btnLogin = view.findViewById(R.id.btnLogIn);

        btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                if(!validarUsuario() | !validarSenha()){
                    return;
                }
                String input = "Usuário: " + txtInputUsuario.getEditText().getText().toString();
                input += "\n";
                input += "Senha: " + txtInputSenha.getEditText().getText().toString();

                Toast.makeText(context, input, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected boolean validarUsuario(){
        String usuarioInput = txtInputUsuario.getEditText().getText().toString().trim();

        if(usuarioInput.isEmpty()){
            txtInputUsuario.setError("Informe o usuário");
            txtInputUsuario.requestFocus();
            return false;
        } else if(usuarioInput.length() > 15){
            txtInputUsuario.setError("Usuário acima do limite de carácteres");
            txtInputUsuario.requestFocus();
            return false;
        }else {
            txtInputUsuario.setError(null);
            return true;
        }
    }

    private boolean validarSenha(){
        String senhaInput = txtInputSenha.getEditText().getText().toString().trim();

        if(senhaInput.isEmpty()){
            txtInputSenha.setError("Informe a senha");
            txtInputSenha.requestFocus();
            return false;
        } else if(!PASSWORD_PATTERN.matcher(senhaInput).matches()){
            txtInputSenha.setError("Senha muito fraca");
            txtInputSenha.requestFocus();
            return false;
        } else {
            txtInputSenha.setError(null);
            return true;
        }
    }
}
