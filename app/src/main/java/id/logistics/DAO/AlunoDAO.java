package id.logistics.DAO;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import id.logistics.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contIds);
        alunos.add(aluno);
        atualizaIDs();
    }

    private void atualizaIDs() {
        contIds++;
    }

    public void edita(Aluno aluno){

        Aluno alunoEncontrado = buscaAlunoId(aluno);

        if(alunoEncontrado != null){
            int posAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posAluno, aluno);
        }

    }

    @Nullable
    private Aluno buscaAlunoId(Aluno aluno) {

        for (Aluno a: alunos){
            if(a.getId() == aluno.getId()){
                return a;
            }
        }

        return null;

    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

}
