package com.udemy.project;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.project.domain.Categoria;
import com.udemy.project.domain.Cidade;
import com.udemy.project.domain.Cliente;
import com.udemy.project.domain.Endereco;
import com.udemy.project.domain.Estado;
import com.udemy.project.domain.Produto;
import com.udemy.project.domain.enums.TipoCliente;
import com.udemy.project.repositories.CategoriaRepository;
import com.udemy.project.repositories.CidadeRepository;
import com.udemy.project.repositories.ClienteRepository;
import com.udemy.project.repositories.EnderecoRepository;
import com.udemy.project.repositories.EstadoRepository;
import com.udemy.project.repositories.ProdutoRepository;

@SpringBootApplication
public class UdemyprojectApplication implements CommandLineRunner {

	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	
	private ClienteRepository clienteRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(UdemyprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "IMpressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 200.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2 ,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria da Silva", "mari.silva@gmail", "32609522843", TipoCliente.PESSOAFISICA);
		
		
		cli1.getTelefones().addAll(Arrays.asList("954441418", "948493625"));
		
		Endereco e1 = new Endereco(null, "Rua tupã", "189", "A", "Oratorio", "09381400 ", cli1 , c1);
		Endereco e2 = new Endereco(null, "Rua tupã", "189", "A", "Oratorio", "09381400 ", cli1 , c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}
}
