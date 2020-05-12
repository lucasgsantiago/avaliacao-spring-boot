package br.com.tokiomarine.seguradora.avaliacao;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.EditarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@LocalServerPort
	private int randomServerPort;
	private RestTemplate restTemplate;
	private String baseUrl;
	private HttpHeaders headers;
	private URI uri;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() throws URISyntaxException {
		restTemplate = new RestTemplate();
		baseUrl = "http://localhost:" + randomServerPort + "/api/v1/estudantes";
		headers = new HttpHeaders();
		uri = new URI(baseUrl);
	}

	@Test
	public void testGetEstudanteListSuccess() throws IOException {
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		EstudanteListResult estudanteList = mapper.readValue(result.getBody(), EstudanteListResult.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertFalse(estudanteList.getEstudantes().isEmpty());
	}


	@Test
	public void testAddEstudanteNomeNuloError()
	{
		AdicionarEstudanteCommand estudante = new AdicionarEstudanteCommand( null, "test@email.com","123456","curso","7199999999");
		HttpEntity<AdicionarEstudanteCommand> request = new HttpEntity<>(estudante, headers);

		try
		{
			restTemplate.postForEntity(uri, request, String.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertTrue(ex.getResponseBodyAsString().contains("Falha na Validação dos campos."));
		}
	}

	@Test
	public void testAddEstudanteEmailNuloError()
	{
		AdicionarEstudanteCommand estudante = new AdicionarEstudanteCommand( "EStudante", null,"123456","curso","7199999999");
		HttpEntity<AdicionarEstudanteCommand> request = new HttpEntity<>(estudante, headers);

		try
		{
			restTemplate.postForEntity(uri, request, String.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertTrue(ex.getResponseBodyAsString().contains("Falha na Validação dos campos."));
		}
	}

	@Test
	public void testAddEstudanteMatriculaError()
	{
		AdicionarEstudanteCommand estudante = new AdicionarEstudanteCommand( "EStudante", "test@email.com",null,"curso","7199999999");
		HttpEntity<AdicionarEstudanteCommand> request = new HttpEntity<>(estudante, headers);

		try
		{
			restTemplate.postForEntity(uri, request, String.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertTrue(ex.getResponseBodyAsString().contains("Falha na Validação dos campos."));
		}
	}

	@Test
	public void testAddEstudanteSuccess()
	{
		AdicionarEstudanteCommand estudante = new AdicionarEstudanteCommand( "Lucas", "lucas@email.com","123456","curso","7199999999");
		HttpEntity<AdicionarEstudanteCommand> request = new HttpEntity<>(estudante, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testAddEstudanteEmailExistenteError()
	{
		AdicionarEstudanteCommand estudante = new AdicionarEstudanteCommand( "outro", "xawoy@tms.com.br","123456","curso","7199999999");
		HttpEntity<AdicionarEstudanteCommand> request = new HttpEntity<>(estudante, headers);

		try
		{
			restTemplate.postForEntity(uri, request, String.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			System.out.println(ex.getResponseBodyAsString());
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertTrue(ex.getResponseBodyAsString().contains("Já existe um Estudante com este E-mail"));
		}
	}

	@Test
	public void testGetEstudanteSuccess() throws URISyntaxException, IOException {
		uri = new URI(baseUrl.concat("/1"));
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		EstudanteResult estudante = mapper.readValue(result.getBody(), EstudanteResult.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(estudante.getNome(),"Xawoy");
	}

	@Test
	public void testEditarEstudanteSuccess() throws URISyntaxException, IOException {
		uri = new URI(baseUrl.concat("/2"));
		EditarEstudanteCommand command = new EditarEstudanteCommand(null, "Estudante Alterado", "estudante.alterado@email.com","123456","curso","7199999999");
		HttpEntity<EditarEstudanteCommand> request = new HttpEntity<>(command, headers);
		restTemplate.put(uri, request);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		EstudanteResult estudante = mapper.readValue(result.getBody(), EstudanteResult.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(estudante.getNome(),"Estudante Alterado");
	}

	@Test
	public void testDeleteEstudanteSuccess() throws URISyntaxException, IOException {
		uri = new URI(baseUrl.concat("/3"));
		try {
			restTemplate.delete(uri);
			ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
			Assert.fail();
		}catch (HttpClientErrorException ex){
			Assert.assertEquals(404, ex.getRawStatusCode());
			Assert.assertTrue(ex.getResponseBodyAsString().contains("Não foi encontrado nenhum Estudante com o ID"));
		}

	}

}
