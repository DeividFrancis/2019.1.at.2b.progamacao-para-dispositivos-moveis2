package com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.erro.ErrorException;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.bean.PessoaPapelBean;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.CondicaoEnum;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.ConnectionDB;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.modal.db.Filtro;
import com.github.deivifrancis.a20191at2bprogamacao_para_dispositivos_moveis.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class PessoaPapelDAO extends ConnectionDB {
    SQLiteDatabase db;
    ContentValues dados;
    private static final String TABELA = "PESSOAPAPEL";

    public PessoaPapelDAO(Context context){
        super(context);
        db = getWritableDatabase();
    }

    public PessoaPapelBean inserir (PessoaPapelBean pessoaPapelBean) throws ErrorException {
        Long pessoaPapelId = null;

        dados = new ContentValues();
        dados.put("pessoa_id",pessoaPapelBean.getPessoaBean().getId());
        dados.put("papel_id",pessoaPapelBean.getPapelBean().getId());
        try {
            pessoaPapelId = db.insertOrThrow(TABELA,null,dados);
        }catch (SQLException e){
            throw new ErrorException("Erro ao definir um papel para essa pessoa.",e);
        }
         pessoaPapelBean.setId(pessoaPapelId.intValue());

        return pessoaPapelBean;
    }

    public PessoaPapelBean atualizar(PessoaPapelBean pessoaPapelBean) throws ErrorException {
        dados = new ContentValues();
        dados.put("pessoa_id",pessoaPapelBean.getPessoaBean().getId());
        dados.put("papel_id",pessoaPapelBean.getPapelBean().getId());
        try {
            db.update(TABELA,dados," _id "+CondicaoEnum.EQUALS.get()+" ?",new String[] {String.valueOf(pessoaPapelBean.getId())});
        }catch (SQLException e){
            throw new ErrorException("Erro ao atualizar o papel da pessoa.",e);
        }

        return  pessoaPapelBean;
    }

    public PessoaPapelBean buscarId(Integer id) throws ErrorException {

        if (id == null){
            throw new ErrorException("Id nulo.");
            //TODO: VERIFICAR NOS OUTROS DAO = NOS BUSCA ID, ID NULO.
        }

        Filtro filtro = new Filtro();
        filtro.adicionar("pepa._id", CondicaoEnum.EQUALS, id);
        return buscar(filtro).get(0);
    }

    public List<PessoaPapelBean> buscar(Filtro filtro) throws ErrorException {
        List<PessoaPapelBean> pessoaPapelBeans = new ArrayList<>();

        String condicoes = "";
        String [] parametros = null;

        if (filtro != null){
            condicoes = filtro.criarCondicao();
            parametros = filtro.criarParametros();
        }

        StringBuilder pessoaPapelQuery = new StringBuilder();
        pessoaPapelQuery.append("    select                                                      ");
        pessoaPapelQuery.append("  		   pepa._id,                                            ");
        pessoaPapelQuery.append("  		   pepa.pessoa_id,                                      ");
        pessoaPapelQuery.append("  		   pepa.papel_id,                                       ");
        pessoaPapelQuery.append("  		   pes.nome,                                            ");
        pessoaPapelQuery.append("  		   pes.cpf,                                             ");
        pessoaPapelQuery.append("  		   pes.aniversario,                                     ");
        pessoaPapelQuery.append("  		   pes.logradouro,                                      ");
        pessoaPapelQuery.append("  		   pes.telefone,                                        ");
        pessoaPapelQuery.append("  		   pes.email,                                           ");
        pessoaPapelQuery.append("  		   pes.senha,                                           ");
        pessoaPapelQuery.append("  		   pes.numero,                                          ");
        pessoaPapelQuery.append("  		   pes.cidade,                                          ");
        pessoaPapelQuery.append("  		   pes.estado,                                          ");
        pessoaPapelQuery.append("  		   pap.descricao                                        ");
        pessoaPapelQuery.append("      from "+TABELA+" pepa                                     ");
        pessoaPapelQuery.append("      join pessoa pes on (pes._id = pepa.pessoa_id)            ");
        pessoaPapelQuery.append("      join papel pap on (pap._id = pepa.papel_id)              ");
        pessoaPapelQuery.append("     where 1 = 1 "+condicoes                                    );
        pessoaPapelQuery.append("  order by pes.nome                                            ");


        Cursor cursor = db.rawQuery(pessoaPapelQuery.toString(), parametros);

        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                PessoaPapelBean pessoaPapelBean = new PessoaPapelBean();
                pessoaPapelBean.setId(cursor.getInt(0));
                pessoaPapelBean.getPessoaBean().setId(cursor.getInt(1));
                pessoaPapelBean.getPapelBean().setId(cursor.getInt(2));
                pessoaPapelBean.getPessoaBean().setNome(cursor.getString(3));
                pessoaPapelBean.getPessoaBean().setCpf(cursor.getString(4));
                pessoaPapelBean.getPessoaBean().setAniversario(DateUtils.parse(cursor.getString(5)));
                pessoaPapelBean.getPessoaBean().setLogradouro(cursor.getString(6));
                pessoaPapelBean.getPessoaBean().setTelefone(cursor.getString(7));
                pessoaPapelBean.getPessoaBean().setEmail(cursor.getString(8));
                pessoaPapelBean.getPessoaBean().setSenha(cursor.getString(9));
                pessoaPapelBean.getPessoaBean().setNumero(cursor.getString(10));
                pessoaPapelBean.getPessoaBean().setCidade(cursor.getString(11));
                pessoaPapelBean.getPessoaBean().setEstado(cursor.getString(12));
                pessoaPapelBean.getPapelBean().setDescricao(cursor.getString(13));
                pessoaPapelBeans.add(pessoaPapelBean);
            }while (cursor.moveToNext());
        }else {
            throw new ErrorException("Papéis não encontrados.");
        }

        return pessoaPapelBeans;
    }

    public void deletar(Integer id){
        db.delete(TABELA," _id "+CondicaoEnum.EQUALS.get()+" ?",new String[] {String.valueOf(id)});
    }

    public void fundir(PessoaPapelBean pessoaPapelBean) throws ErrorException {
        try {
            buscarId(pessoaPapelBean.getId());
            atualizar(pessoaPapelBean);
        }catch (ErrorException e){
            inserir(pessoaPapelBean);
        }
    }
}
