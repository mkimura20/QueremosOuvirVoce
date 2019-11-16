package parasolution.queremosouvirvoce.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import parasolution.queremosouvirvoce.R;
import parasolution.queremosouvirvoce.fragment.CadastroClienteFragment;
import parasolution.queremosouvirvoce.fragment.CategoriasFragment;
import parasolution.queremosouvirvoce.fragment.InicialFragment;
import parasolution.queremosouvirvoce.fragment.LoginFragment;
import parasolution.queremosouvirvoce.fragment.PerguntaFinalFragment;
import parasolution.queremosouvirvoce.fragment.PerguntasFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Instanciando o objeto nav_view, menu lateral
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //metodo que da suporte para que o fragmento possa ser exibido na activity_main
        fragmentManager = getSupportFragmentManager();

        //metodo que ira trocar o fragmento a ser exibido
        //neste caso precisa ser passado o nome do layout onde o fagmento sera exibido e o nome da classe do fragmento
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.content_fragment, new InicialFragment()).commit();

        navigationView.bringToFront();

        //addFragment();
    }

    private void addFragment() {
        Fragment fragment;

        fragment = fragmentManager.findFragmentById(R.id.content_fragment);
        if(fragment instanceof InicialFragment){
            fragment = new CategoriasFragment();
        }else if(fragment instanceof CategoriasFragment){
            fragment = new PerguntasFragment();
        }else if(fragment instanceof PerguntasFragment){
            fragment = new PerguntaFinalFragment();
        }else if(fragment instanceof PerguntaFinalFragment){
            fragment = new CadastroClienteFragment();
        }else if(fragment instanceof CadastroClienteFragment){
            fragment = new InicialFragment();
        }else {
            fragment = new InicialFragment();
        }

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, fragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_sair) {

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //método para pegar a informacao do item do menu que foi selecionado pelo usuário
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_administrador) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new LoginFragment()).commit();

        } else if (id == R.id.nav_cadastro_cliente) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new CadastroClienteFragment()).commit();

        } else if (id == R.id.nav_perguntas) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new InicialFragment()).commit();

        } else if (id == R.id.nav_configuracoes) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new InicialFragment()).commit();

        } else if (id == R.id.nav_compartilhar) {
            fragmentManager.beginTransaction().replace(R.id.content_fragment, new InicialFragment()).commit();

        } else if (id == R.id.nav_sobre){

        }

        item.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
