package org.example.controller;

import org.example.model.Categoria;
import org.example.model.Solicitacao;
import org.example.model.Status;
import org.example.service.SolicitacaoService;

import java.util.Scanner;

public class SolicitacaoController {

    private SolicitacaoService service = new SolicitacaoService();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int op;

        do {
            System.out.println("\n1 - Criar Solicitação");
            System.out.println("2 - Listar Solicitações");
            System.out.println("3 - Buscar por Protocolo");
            System.out.println("4 - Atualizar Status");
            System.out.println("0 - Sair");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> criar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> atualizar();
            }

        } while (op != 0);
    }

    private void criar() {
        System.out.println("Categoria: 1-ILUMINACAO 2-BURACO 3-LIMPEZA 4-SAUDE 5-SEGURANCA");
        int c = sc.nextInt(); sc.nextLine();

        System.out.println("Descrição:");
        String desc = sc.nextLine();

        System.out.println("Localização:");
        String loc = sc.nextLine();

        System.out.println("Anônimo? (sim/nao)");
        String resposta = sc.nextLine().toLowerCase();

        boolean anon = resposta.equals("sim");

        service.criarSolicitacao(Categoria.values()[c-1], desc, loc, anon);
    }

    private void listar() {
        for (Solicitacao s : service.listar()) {
            System.out.println(s);
        }
    }

    private void buscar() {
        System.out.println("Protocolo:");
        String p = sc.nextLine();

        Solicitacao s = service.buscar(p);
        if (s != null) {
            System.out.println(s);
            s.getHistorico().forEach(System.out::println);
        }
    }

    private void atualizar() {
        System.out.println("Protocolo:");
        String p = sc.nextLine();

        System.out.println("Novo status: 1-TRIAGEM 2-EM_EXECUCAO 3-RESOLVIDO 4-ENCERRADO");
        int st = sc.nextInt(); sc.nextLine();

        System.out.println("Comentário:");
        String com = sc.nextLine();

        service.atualizarStatus(p, Status.values()[st], com);
    }
}