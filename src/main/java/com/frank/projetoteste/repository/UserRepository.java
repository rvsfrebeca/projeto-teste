package com.frank.projetoteste.repository;

import com.frank.projetoteste.model.User;
import com.frank.projetoteste.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@AllArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String TABLE_NAME = "user_table";

    private final String GET_ALL_USERS = "SELECT id, nome, telefone, email FROM user_table";

    private final String GET_USER = "SELECT * FROM user_table WHERE id = ?";

    private final String INSERT_USER = "INSERT INTO user_table (id ,nome, telefone, email) VALUES (?, ?, ?, ?)";

    private final String UPDATE_USER = "UPDATE user_table SET nome = ?, telefone = ?, email = ? WHERE id = ?";

    private final String DELETE_USER = "DELETE FROM user_table WHERE id = ?";

    public List<User> getAllUsers(){
        return jdbcTemplate.query(GET_ALL_USERS, userRowMapper());
    }

    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> User.builder()
                .id(rs.getObject("id", UUID.class))
                .nome(rs.getString("nome"))
                .email(rs.getString("email"))
                .telefone(rs.getString("telefone"))
                .build();
    }

    public User getUser(UUID id) {
        return jdbcTemplate.queryForObject(GET_USER, userRowMapper(), id);
    }

    public User createUser(UserDTO userReceived) {
        SimpleJdbcInsert insert = getJdbcInsert();

        UUID userId = UUID.randomUUID();

        Map<String, Object> params = buildParams(userReceived, userId);

        insert.execute(params);
        return userReceived.toController(userId);
    }

    public Map<String, Object> buildParams(UserDTO user, UUID userId){
        Map<String, Object> params = new HashMap<>();
        params.put("id", userId);
        params.put("nome", user.getNome());
        params.put("telefone", user.getTelefone());
        params.put("email", user.getEmail());
        return params;
    }

    public SimpleJdbcInsert getJdbcInsert(){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        return jdbcInsert.withTableName(TABLE_NAME);
    }


    public void updateUser(UserDTO userToUpdate, UUID id) {
        jdbcTemplate.update(
                UPDATE_USER,
                userToUpdate.getNome(),
                userToUpdate.getTelefone(),
                userToUpdate.getEmail(),
                id);
    }

    public void deleteUser(UUID id) {
        jdbcTemplate.update(DELETE_USER, id);
    }
}
