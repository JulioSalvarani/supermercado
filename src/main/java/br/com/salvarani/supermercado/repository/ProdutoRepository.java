package br.com.salvarani.supermercado.repository;

import br.com.salvarani.supermercado.model.Produto;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class ProdutoRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String INSERIR_PRODUTO = "INSERT INTO produto(descricao, lote, dataValidade, dataCriacao) VALUES(?,?,?,?)";
    private static final String BUSCAR_TODOS = "SELECT * FROM produto";
    private static final String BUSCAR_POR_ID = "SELECT * FROM produto WHERE id = ?";

    private static final String VALIDA_ID = "SELECT COUNT (*) FROM produto WHERE id = ?";

    private Produto rowMapperProduto(ResultSet rs) throws SQLException {
        return Produto.builder()
                .id(rs.getLong("id"))
                .descricao(rs.getString("descricao"))
                .lote(rs.getString("lote"))
                .dataValidade(rs.getDate("dataValidade").toLocalDate())
                .dataCriacao(rs.getDate("dataCriacao").toLocalDate())
                .build();
    }

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> buscartodos() {
        List<Produto> listaprodutos = jdbcTemplate.query(BUSCAR_TODOS, (rs, rowNum) -> rowMapperProduto(rs));
        return listaprodutos;
    }


    public Produto salvarProduto(Produto produto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(INSERIR_PRODUTO, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, produto.getDescricao());
                    ps.setString(2, produto.getLote());
                    ps.setDate(3, Date.valueOf(produto.getDataValidade()));
                    ps.setDate(4, Date.valueOf(produto.getDataCriacao()));

                    return ps;
        },keyHolder);


        Number id = keyHolder.getKey();
        produto.setId(id.longValue());
        return produto;

    }

    public Produto buscarPorId(Long id) {
        Produto itemBusca = jdbcTemplate.queryForObject(BUSCAR_POR_ID, (rs, rowNum) -> rowMapperProduto(rs), id);
        Long itemId = itemBusca.getId();
        Integer validaID = jdbcTemplate.queryForObject(VALIDA_ID, Integer.class, itemId);
        if (validaID == null || validaID <= 0) {
            return null;
        }
        return itemBusca;        
    }

    public Produto alterarPorId(Long id){
        Produto produtoSelecionado = buscarPorId(id);
        return produtoSelecionado;
    }
}

