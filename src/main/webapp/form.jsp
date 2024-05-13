<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire d'Informations Médicales</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
            text-align: center;
        }
        form {
            margin-top: 20px;
            border-top: 1px solid #ddd;
            padding-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"],
        input[type="number"],
        input[type="date"],
        select,
        textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>

</head>
<body>
<div class="container">
<h1>Formulaire d'Informations Médicales</h1>
        
    <!-- Formulaire pour saisir ou récupérer les informations médicales -->
    <form action="MedicalServlet" method="post">
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" value="${nom}" required><br>
    <label for="prenom">Prénom:</label>
    <input type="text" id="prenom" name="prenom" value="${prenom}" required><br>
    <label for="dateNaissance">Date de Naissance:</label>
    <input type="date" id="dateNaissance" name="dateNaissance" value="${dateNaissance}"><br>
    <label for="sexe">Sexe:</label>
    <select id="sexe" name="sexe">
        <option value="Homme" ${sexe == 'Homme' ? 'selected' : ''}>Homme</option>
        <option value="Femme" ${sexe == 'Femme' ? 'selected' : ''}>Femme</option>
        <option value="Autre" ${sexe == 'Autre' ? 'selected' : ''}>Autre</option>
    </select><br>
    <label for="taille">Taille (cm):</label>
    <input type="number" id="taille" name="taille" value="${taille}"><br>
    <label for="poids">Poids (kg):</label>
    <input type="number" id="poids" name="poids" value="${poids}"><br>
    <label for="groupeSanguin">Groupe Sanguin:</label>
    <select id="groupeSanguin" name="groupeSanguin">
        <option value="A+" ${groupeSanguin == 'A+' ? 'selected' : ''}>A+</option>
        <option value="A-" ${groupeSanguin == 'A-' ? 'selected' : ''}>A-</option>
        <option value="B+" ${groupeSanguin == 'B+' ? 'selected' : ''}>B+</option>
        <option value="B-" ${groupeSanguin == 'B-' ? 'selected' : ''}>B-</option>
        <option value="AB+" ${groupeSanguin == 'AB+' ? 'selected' : ''}>AB+</option>
        <option value="AB-" ${groupeSanguin == 'AB-' ? 'selected' : ''}>AB-</option>
        <option value="O+" ${groupeSanguin == 'O+' ? 'selected' : ''}>O+</option>
        <option value="O-" ${groupeSanguin == 'O-' ? 'selected' : ''}>O-</option>
    </select><br>
    <label for="allergies">Allergies:</label>
    <textarea id="allergies" name="allergies" rows="4">${allergies}</textarea><br>
    <label for="antecedents">Antécédents Médicaux:</label>
    <textarea id="antecedents" name="antecedents" rows="4">${antecedents}</textarea><br>
    <input type="submit" name="action" value="Enregistrer">
    <input type="submit" name="action" value="Afficher Informations Médicales">
</form>

</div>
</body>
</html>