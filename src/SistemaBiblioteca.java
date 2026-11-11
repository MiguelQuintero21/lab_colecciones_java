

import java.util.*;

public class SistemaBiblioteca {
    private Map<String, Material> catalogo;

    private Map<String, List<String>> listaEspera;

    private Set<String> miembros;

    public SistemaBiblioteca() {
        this.catalogo = new HashMap<>();
        this.listaEspera = new HashMap<>();
        this.miembros = new HashSet<>();
    }

    public void registrarMaterial(Material m) {
        catalogo.put(m.getCodigo(), m);
    }

    public Material buscarMaterial(String codigo) {
        return catalogo.get(codigo);
    }

    public void mostrarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE LA BIBLIOTECA ---");
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        for (Map.Entry<String, Material> entry : catalogo.entrySet()) {
            entry.getValue().mostrarInfo();
        }
    }

    public void agregarEspera(String codigoMaterial, String usuario) {
        listaEspera.putIfAbsent(codigoMaterial, new ArrayList<>());
        listaEspera.get(codigoMaterial).add(usuario);
    }

    public String siguienteEnEspera(String codigoMaterial) {
        List<String> cola = listaEspera.get(codigoMaterial);
        if (cola != null && !cola.isEmpty()) {
            return cola.remove(0);
        }
        return null;
    }

    public boolean registrarMiembro(String usuario) {
        return miembros.add(usuario); // retorna true si se agregó, false si ya existía
    }

    public boolean tieneMembresia(String usuario) {
        return miembros.contains(usuario);
    }






    public static void main(String[] args) {
        SistemaBiblioteca biblioteca = new SistemaBiblioteca();

        System.out.println("=== 1. PRUEBA DE CATÁLOGO  ===");
        Libro libro1 = new Libro("BK-001", "la biblia", 2026, true, "autor pedro", "978-0307474728");
        Libro libro2 = new Libro("BK-002", "biblia", 2004, false, "autor mateo", "978-8424198");
        Revista rev1 = new Revista("RV-101", "xd", 2024, true, 450, "adolescentes");
        Revista rev2 = new Revista("RV-102", "disney", 2026, false, 12, "niños");

        biblioteca.registrarMaterial(libro1);
        biblioteca.registrarMaterial(libro2);
        biblioteca.registrarMaterial(rev1);
        biblioteca.registrarMaterial(rev2);

        biblioteca.mostrarCatalogo();

        System.out.println("\nBuscando código BK-001:");
        Material encontrado = biblioteca.buscarMaterial("BK-001");
        if (encontrado != null) encontrado.mostrarInfo();

        System.out.println("Buscando código inexistente (BK-999):");
        Material noEncontrado = biblioteca.buscarMaterial("BK-999");
        System.out.println(noEncontrado == null ? "Resultado: El material no existe (retornó null)." : "");

        System.out.println("\n=== 2. PRUEBA DE LISTA DE ESPERA  ===");
        biblioteca.agregarEspera("BK-002", "Ana Gómez");
        biblioteca.agregarEspera("BK-002", "Carlos Pérez");
        biblioteca.agregarEspera("BK-002", "Lucía Torres");

        System.out.println("Atendiendo al primer usuario en espera para el libro BK-002: " + biblioteca.siguienteEnEspera("BK-002"));
        System.out.println("Atendiendo al siguiente usuario en espera: " + biblioteca.siguienteEnEspera("BK-002"));

        System.out.println("\n=== 3. PRUEBA DE MEMBRESÍAS ===");
        System.out.println("Registrando a 'profe Ana': " + biblioteca.registrarMiembro("Ana"));
        System.out.println("Registrando a 'Profe Nath': " + biblioteca.registrarMiembro("Nath"));
        System.out.println("Intentando registrar a 'profe Ana' otra vez (duplicado): " + biblioteca.registrarMiembro("profe Ana"));

        System.out.println("¿Tiene 'Ana' membresía activa? " + biblioteca.tieneMembresia("Ana"));
        System.out.println("¿Tiene 'Nath' membresía activa? " + biblioteca.tieneMembresia("nath"));
    }
}