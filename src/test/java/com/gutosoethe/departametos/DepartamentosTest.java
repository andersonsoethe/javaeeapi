package com.gutosoethe.departametos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gutosoethe.model.Departamento;

import io.restassured.RestAssured;

@RunWith(Arquillian.class)
public class DepartamentosTest {
    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Departamento.class)
                .addAsManifestResource("jbossas-ds.xml", "WEB-INF/jbossas-ds.xml")
                .addAsManifestResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8080"; // Defina a URL base da API
    }

    @Before
    public void populaBanco(){

        Departamento departamento = new Departamento();
        departamento.setDescricao("Recursos Humanos");
        departamento.setNome("Departamento de RH");
        entityManager.persist(departamento);
    }

    @Test
    public void testPostDepartamento(){
        JsonObjectBuilder body = Json.createObjectBuilder();
        body.add("nome", "Recursos Humanos");
        body.add("descricao", "Departamento de RH");

        String requestBody = body.build().toString();

        given()
                .contentType("application/json")
                .body(requestBody)
                .when().post("/javaApi/api/departamentos")
                .then()
                .statusCode(201);
    }

    @Test
    public void testGetDepartamento() {
        given()
                .when().get("/javaApi/api/departamentos")
                .then()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[0].nome", equalTo("Recursos Humanos"))
                .body("[0].descricao", equalTo("Departamento de RH"));
    }

    @Test
    public void testGetDepartamentoById() {
        given()
                .when().get("/javaApi/api/departamentos/1")
                .then()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[0].nome", equalTo("Recursos Humanos"))
                .body("[0].descricao", equalTo("Departamento de RH"));
    }

    @Test
    public void testPutDepartamento() {
        JsonObjectBuilder body = Json.createObjectBuilder();
        body.add("nome", "Recursos Humanos 2");
        body.add("descricao", "Departamento de RH 2");

        String requestBody = body.build().toString();

        given()
                .contentType("application/json")
                .body(requestBody)
                .when().put("/javaApi/api/departamentos")
                .then()
                .statusCode(204);
    }

    @Test
    public void testDeleteDepartamento(){
        given()
                .when().delete("/javaApi/api/departamentos/1")
                .then()
                .statusCode(204);
    }

}