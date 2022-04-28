package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.DAOCliente;
import model.Cliente;
import spark.Request;
import spark.Response;

public class ClienteService {

	private DAOCliente clienteDAO = new DAOCliente();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_CPF = 3;
	
	
	public ClienteService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Cliente(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Cliente(), orderBy);
	}

	
	public void makeForm(int tipo, Cliente cliente, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		String umCliente = "";
		if(tipo != FORM_INSERT) {
			umCliente += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/cliente/list/1\">Novo Cliente</a></b></font></td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t</table>";
			umCliente += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/cliente/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Cliente";
				nome = "João, Gabriel, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + cliente.getId_cliente();
				name = "Atualizar Cliente (ID " + cliente.getId_cliente() + ")";
				nome = cliente.getNome();
				buttonLabel = "Atualizar";
			}
			umCliente += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umCliente += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\"\" placehoder=\""+nome+"\"></td>";
			umCliente += "\t\t\t<td>Biografia: <input class=\"input--register\" type=\"text\" name=\"biografia\" value=\""+ cliente.getBiografia() +"\"></td>";
			umCliente += "\t\t\t<td>CPF: <input class=\"input--register\" type=\"text\" name=\"cpf\" value=\""+ cliente.getCpf() +"\"></td>";
			umCliente += "\t\t\t<td>RG: <input class=\"input--register\" type=\"text\" name=\"rg\" value=\""+ cliente.getRg() +"\"></td>";
			umCliente += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t</table>";
			umCliente += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umCliente += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Cliente (ID " + cliente.getId_cliente() + ")</b></font></td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t\t<tr>";
			umCliente += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ cliente.getNome() +"\"></td>";
			umCliente += "\t\t\t<td>Biografia: <input class=\"input--register\" type=\"text\" name=\"biografia\" value=\""+ cliente.getBiografia() +"\"></td>";
			umCliente += "\t\t\t<td>CPF: <input class=\"input--register\" type=\"text\" name=\"cpf\" value=\""+ cliente.getCpf() +"\"></td>";
			umCliente += "\t\t\t<td>RG: <input class=\"input--register\" type=\"text\" name=\"rg\" value=\""+ cliente.getRg() +"\"></td>";
			umCliente += "\t\t\t<td>&nbsp;</td>";
			umCliente += "\t\t</tr>";
			umCliente += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-CLIENTE>", umCliente);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Clientes</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/cliente/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/cliente/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/cliente/list/" + FORM_ORDERBY_CPF + "\"><b>CPF</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Cliente> clientes;
		if (orderBy == FORM_ORDERBY_ID) {                 	clientes = clienteDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_NOME) {		clientes = clienteDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_CPF) {			clientes = clienteDAO.getOrderByCpf();
		} else {											clientes = clienteDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Cliente p : clientes) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getId_cliente() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getCpf() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/cliente/" + p.getId_cliente() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/cliente/update/" + p.getId_cliente() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteCliente('" + p.getId_cliente() + "', '" + p.getNome() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-CLIENTE>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		String biografia = request.queryParams("biografia");
		String cpf = request.queryParams("cpf");
		String rg = request.queryParams("rg");
		
		String resp = "";
		
		Cliente cliente = new Cliente(-1,nome,cpf,rg,biografia);
		
		if(clienteDAO.insert(cliente) == true) {
            resp = "Cliente (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Cliente (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Cliente cliente = (Cliente) clienteDAO.get(id);
		
		if (cliente != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, cliente, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Cliente " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Cliente cliente = (Cliente) clienteDAO.get(id);
		
		if (cliente != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, cliente, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Cliente " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Cliente cliente = clienteDAO.get(id);
        String resp = "";       

        if (cliente != null) {
        	cliente.setNome(request.queryParams("nome"));
        	cliente.setBiografia(request.queryParams("biografia"));
        	cliente.setCpf(request.queryParams("cpf"));
        	cliente.setRg(request.queryParams("rg"));
        	clienteDAO.update(cliente);
        	response.status(200); // success
            resp = "Cliente (ID " + cliente.getId_cliente() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Cliente (ID \" + cliente.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Cliente cliente = clienteDAO.get(id);
        String resp = "";       

        if (cliente != null) {
            clienteDAO.delete(id);
            response.status(200); // success
            resp = "Cliente (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Cliente (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
