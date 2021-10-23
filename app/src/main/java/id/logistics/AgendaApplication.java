package id.logistics;

import android.app.Application;

import id.logistics.DAO.AlunoDAO;
import id.logistics.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        criaAlunosIniciais();

    }

    private void criaAlunosIniciais() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Junior", "11999999999", "junior@gmail.com"));
        dao.salva(new Aluno("Daia", "11999999999", "daia@gmail.com"));
    }

}
