//package com.gutosoethe.pessoas;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//import javax.inject.Inject;
//import javax.json.Json;
//import javax.json.JsonObjectBuilder;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.gutosoethe.dao.DepartamentoJpaDao;
//import com.gutosoethe.dao.PessoaJpaDao;
//import com.gutosoethe.model.Departamento;
//import com.gutosoethe.model.Pessoa;
//
//import io.restassured.RestAssured;
//
//
//public class PessoasTest {
//
//    @Inject
//    DepartamentoJpaDao departamentoJpaDao;
//
//    @Inject
//    PessoaJpaDao pessoaJpaDao;
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(Pessoa.class)
//                .addClass(PessoaJpaDao.class)
//                .addClass(Departamento.class)
//                .addClass(DepartamentoJpaDao.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//
//    @BeforeClass
//    public static void setUp() {
//        RestAssured.baseURI = "http://localhost:8080"; // Defina a URL base da API
//    }
//
//    @Before
//    public void populaBanco(){
//        Departamento departamento = new Departamento();
//        departamento.setDescricao("Recursos Humanos");
//        departamento.setNome("Departamento de RH");
//        departamentoJpaDao.save(departamento);
//
//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome("Anderson Teste");
//        pessoa.setEmail("anderson@gmail.com");
//        pessoa.setPhone("475555543555");
//        pessoa.setIdade(25);
//        pessoa.setDepartamento(departamento);
//        pessoaJpaDao.save(pessoa);
//
//    }
//
//    @Test
//    public void testPostPessoa() {
//        JsonObjectBuilder bodyDepartamento = Json.createObjectBuilder();
//        bodyDepartamento.add("id", "1");
//        bodyDepartamento.build();
//
//        JsonObjectBuilder body = Json.createObjectBuilder();
//        body.add("nome", "Anderson Teste");
//        body.add("email", "anderson@gmail.com");
//        body.add("phone", "475555543555");
//        body.add("idade", "25");
//        body.add("Departamento", bodyDepartamento);
//
//        String requestBody = body.build().toString();
//
//        given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when().post("/javaApi/api/pessoas") // Substitua pelo seu endpoint real
//                .then()
//                .statusCode(201); // Verifique o código de status esperado após o POST
//    }
//
//    @Test
//    public void testGetPessoa() {
//        given()
//                .when().get("/javaApi/api/pessoas") // Substitua pelo seu endpoint real
//                .then()
//                .statusCode(200)
//                .body("[0].id", equalTo(1))
//                .body("[0].nome", equalTo("Anderson Teste"))
//                .body("[0].email", equalTo("anderson@gmail.com"))
//                .body("[0].phone", equalTo("475555543555"))
//                .body("[0].idade", equalTo(29))
//                .body("[0].departamento.id", equalTo(1))
//                .body("[0].departamento.nome", equalTo("Recursos Humanos"))
//                .body("[0].departamento.descricao", equalTo("Departamento de RH"));
//    }
//
//    @Test
//    public void testGetPessoaById() {
//        given()
//                .when().get("/javaApi/api/pessoas/1")
//                .then()
//                .statusCode(200)
//                .body("[0].id", equalTo(1))
//                .body("[0].nome", equalTo("Crud Generics 4"))
//                .body("[0].email", equalTo("Crud@gmail.com"))
//                .body("[0].phone", equalTo("99999999"))
//                .body("[0].idade", equalTo(29))
//                .body("[0].departamento.id", equalTo(1))
//                .body("[0].departamento.nome", equalTo("Recursos Humanos"))
//                .body("[0].departamento.descricao", equalTo("Departamento de RH"));
//    }
//
//    @Test
//    public void testPutPessoa() {
//        JsonObjectBuilder bodyDepartamento = Json.createObjectBuilder();
//        bodyDepartamento.add("id", "1");
//        bodyDepartamento.build();
//
//        JsonObjectBuilder body = Json.createObjectBuilder();
//        body.add("nome", "Anderson Teste");
//        body.add("email", "anderson@gmail.com");
//        body.add("phone", "475555543555");
//        body.add("idade", "25");
//        body.add("Departamento", bodyDepartamento);
//
//        String requestBody = body.build().toString();
//
//        given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when().put("/javaApi/api/pessoas")
//                .then()
//                .statusCode(204);
//    }
//
//    @Test
//    public void testDeleteDepartamento(){
//        given()
//                .when().delete("/javaApi/api/pessoas/1")
//                .then()
//                .statusCode(204);
//    }
//}