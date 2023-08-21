package integration;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.gutosoethe.model.Pessoa;

@RunWith(Arquillian.class)
public class ArquillianWildFlyTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Pessoa.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testSomething() {
    Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste Nome");
        pessoa.setEmail("teste@email.com");
        pessoa.setIdade(25);
        pessoa.setPhone("475555543555");
        assertTrue(true); // Just a placeholder assertion
    }
}
