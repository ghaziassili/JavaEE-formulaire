import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MedicalServlet")
public class MedicalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("Afficher Informations Médicales".equals(action)) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");

            String URL = "jdbc:mysql://localhost/medical_db";
            String USERNAME = "root";
            String PASSWORD = "root";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                PreparedStatement stmt = con.prepareStatement("SELECT * FROM medical_info WHERE nom=? AND prenom=?");
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Affichage des informations médicales
                    request.setAttribute("nom", nom);
                    request.setAttribute("prenom", prenom);
                    request.setAttribute("dateNaissance", rs.getString("date_naissance"));
                    request.setAttribute("sexe", rs.getString("sexe"));
                    request.setAttribute("taille", rs.getInt("taille"));
                    request.setAttribute("poids", rs.getInt("poids"));
                    request.setAttribute("groupeSanguin", rs.getString("groupe_sanguin"));
                    request.setAttribute("allergies", rs.getString("allergies"));
                    request.setAttribute("antecedents", rs.getString("antecedents"));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
                    dispatcher.forward(request, response);
                    System.out.println("Affichage des informations médicales");
                } else {
                	response.sendRedirect("form.jsp");
                    // Aucune information médicale trouvée
                    System.out.println("Aucune information médicale trouvée");
                    // Redirection vers une page d'erreur ou un message approprié
                }

                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'exception, afficher un message d'erreur approprié, rediriger vers une page d'erreur, etc.
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("Enregistrer".equals(action)) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dateNaissance = request.getParameter("dateNaissance");
            String sexe = request.getParameter("sexe");
            int taille = Integer.parseInt(request.getParameter("taille"));
            int poids = Integer.parseInt(request.getParameter("poids"));
            String groupeSanguin = request.getParameter("groupeSanguin");
            String allergies = request.getParameter("allergies");
            String antecedents = request.getParameter("antecedents");

            String URL = "jdbc:mysql://localhost/medical_db";
            String USERNAME = "root";
            String PASSWORD = "root";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM medical_info WHERE nom = ? AND prenom = ?");
                checkStmt.setString(1, nom);
                checkStmt.setString(2, prenom);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    PreparedStatement updateStmt = con.prepareStatement("UPDATE medical_info SET date_naissance=?, sexe=?, taille=?, poids=?, groupe_sanguin=?, allergies=?, antecedents=? WHERE nom=? AND prenom=?");
                    updateStmt.setString(1, dateNaissance);
                    updateStmt.setString(2, sexe);
                    updateStmt.setInt(3, taille);
                    updateStmt.setInt(4, poids);
                    updateStmt.setString(5, groupeSanguin);
                    updateStmt.setString(6, allergies);
                    updateStmt.setString(7, antecedents);
                    updateStmt.setString(8, nom);
                    updateStmt.setString(9, prenom);

                    int rowsAffected = updateStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        // La mise à jour a réussi
                        System.out.println("La mise à jour a réussi");
                    } else {
                        // La mise à jour a échoué
                        System.out.println("La mise à jour a échoué");
                    }
                    updateStmt.close();
                } else {
                    PreparedStatement insertStmt = con.prepareStatement("INSERT INTO `medical_info`( `nom`, `prenom`, `date_naissance`, `sexe`, `taille`, `poids`, `groupe_sanguin`, `allergies`, `antecedents`) VALUES (?,?,?,?,?,?,?,?,?)");
                    insertStmt.setString(1, nom);
                    insertStmt.setString(2, prenom);
                    insertStmt.setString(3, dateNaissance);
                    insertStmt.setString(4, sexe);
                    insertStmt.setInt(5, taille);
                    insertStmt.setInt(6, poids);
                    insertStmt.setString(7, groupeSanguin);
                    insertStmt.setString(8, allergies);
                    insertStmt.setString(9, antecedents);

                    int rowsAffected = insertStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        // L'insertion a réussi
                        System.out.println("L'insertion a réussi");
                    } else {
                        // L'insertion a échoué
                        System.out.println("L'insertion a échoué");
                    }
                    insertStmt.close();
                }

                rs.close();
                checkStmt.close();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
                // Gérer l'exception, afficher un message d'erreur approprié, rediriger vers une page d'erreur, etc.
            }
            // Redirection vers une page de confirmation ou une autre action après l'enregistrement
            response.sendRedirect("form.jsp");
        }else if ("Afficher Informations Médicales".equals(action)) {
            // Si aucune information médicale n'est trouvée, lancez la méthode doGet
            doGet(request, response);
        }
        
    }
}
