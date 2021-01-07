package projet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import projet.dataStructure.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XmlReader {
    HashMap<String, Cours> coursMap;
    HashMap<String, Program> programMap;
    HashMap<String, Student> studentMap;


    public XmlReader(String path){
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file); // ouverture et lecture du fichier XML
            doc.getDocumentElement().normalize(); // normalise le contenu du fichier, opération très conseillée
            Element root = doc.getDocumentElement(); // la racine de l'arbre XML

            // c'est parti pour l'exploration de l'arbre

            List<Element> coursElements = getChildren(root, "course");
            this.coursMap= parseCoursElements(coursElements);

            List<Element> programElements = getChildren(root, "program");
            this.programMap= parseProgramElements(programElements, coursMap);

            List<Element> studentElements = getChildren(root, "student");
            this.studentMap= parseStudentElements(studentElements, programMap);

                    } catch (Exception e) {
            // oups, pas normal
        }
    }

    // Extrait la liste des fils de l'élément item dont le tag est name
    private static List<Element> getChildren(Element item, String name) {
        NodeList nodeList = item.getChildNodes();
        List<Element> children = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeList.item(i); // cas particulier pour nous où tous les noeuds sont des éléments
                if (element.getTagName().equals(name)) {
                    children.add(element);
                }
            }
        }
        return children;
    }

    private static HashMap<String, Cours> parseCoursElements(List<Element> coursElements){
        HashMap<String, Cours> coursMap= new HashMap<>();
        for (Element elem : coursElements){
            try {
                String code = getChildren(elem, "identifier").get(0).getTextContent().strip();
                String name = getChildren(elem, "name").get(0).getTextContent().strip();
                int credits = Integer.parseInt(getChildren(elem,"credits").get(0).getTextContent().strip());
                coursMap.put(code, new Cours(code,name,credits));
            } catch (Exception e) {
                // oups, pas normal
            }
        }
        return coursMap;
    }


    private static HashMap<String, Program> parseProgramElements(List<Element> programElements, HashMap<String, Cours> coursMap ){
        HashMap<String, Program> programMap = new HashMap<>();
        for (Element elem : programElements){
            try {
                String code = getChildren(elem, "identifier").get(0).getTextContent().strip();
                String name = getChildren(elem, "name").get(0).getTextContent().strip();

                List<Element> simpleBlocs = getChildren(elem, "item");
                List<Element> compositBlocs = getChildren(elem, "composite");
                List<Element> optionalBlocs = getChildren(elem, "option");

                List<Bloc> blocs = new ArrayList<>();

                addSimpleBlocs(blocs,simpleBlocs,coursMap);
                addCompositBlocs(blocs,compositBlocs,coursMap);
                addOptionalBlocs(blocs,optionalBlocs,coursMap);

                programMap.put(code, new Program(code,name,blocs));
            } catch (Exception e) {
                // oups, pas normal
            }
        }
        return programMap;
    }

    private static void addSimpleBlocs(List<Bloc> blocs, List<Element> simpleBlocs, HashMap<String, Cours> coursMap){
        for (Element elem : simpleBlocs){
            String code = elem.getTextContent().strip();
            Cours cours = coursMap.get(code);
            List<Cours> coursList =new ArrayList<>(1);
            coursList.add(cours);
            String name = cours.getName();
            blocs.add(new CompositBloc(name,code,coursList));
        }
    }

    private static void addCompositBlocs(List<Bloc> blocs, List<Element> compositBlocs, HashMap<String, Cours> coursMap){
        for (Element elem : compositBlocs){
            String code = getChildren(elem, "identifier").get(0).getTextContent().strip();
            String name = getChildren(elem, "name").get(0).getTextContent().strip();

            List<Element> coursIds = getChildren(elem, "item");

            List<Cours> coursList =new ArrayList<>(5);
            for (Element coursId : coursIds){
                Cours cours = coursMap.get(coursId.getTextContent().strip());
                coursList.add(cours);
            }

            blocs.add(new CompositBloc(name,code,coursList));
        }
    }

    private static void addOptionalBlocs(List<Bloc> blocs, List<Element> optionalBlocs, HashMap<String, Cours> coursMap){
        for (Element elem : optionalBlocs){
            String code = getChildren(elem, "identifier").get(0).getTextContent().strip();
            String name = getChildren(elem, "name").get(0).getTextContent().strip();

            List<Element> coursIds = getChildren(elem, "item");

            List<Cours> coursList =new ArrayList<>(5);
            for (Element coursId : coursIds){
                Cours cours = coursMap.get(coursId.getTextContent().strip());
                coursList.add(cours);
            }

            blocs.add(new OptionBloc(name,code,coursList));
        }
    }

    private static HashMap<String, Student> parseStudentElements(List<Element> studentsElements, HashMap<String, Program> programMap){
        HashMap<String, Student> studentMap = new HashMap<>();
        for (Element elem : studentsElements){
            try {
                String id = getChildren(elem, "identifier").get(0).getTextContent().strip();
                String name = getChildren(elem, "name").get(0).getTextContent().strip();
                String surname = getChildren(elem, "surname").get(0).getTextContent().strip();
                Program program = programMap.get(getChildren(elem, "program").get(0).getTextContent().strip());

                List<Element> gradesList = getChildren(elem, "grade");

                HashMap<String,Double> grades = new HashMap<>();
                for (Element grade : gradesList){
                    String code = getChildren(grade,"item").get(0).getTextContent().strip();
                    String note = getChildren(grade,"value").get(0).getTextContent().strip();
                    Double value;
                    try {
                        if (note.equals("ABI")){
                            value = -10.0;
                        } else {
                            value = Double.parseDouble(note);
                        }
                    } catch (Exception e){
                        System.out.println("La note "+note+ " dans l'UE"+ code +" n'a pas été prise en compte pour l'étudiant numéro"+ id);
                        value = -10.0;
                    }

                    grades.put(code,value);
                }

                studentMap.put(id, new Student(id,name,surname,program,grades));

            } catch (Exception e) {
                System.out.println(getChildren(elem, "surname").get(0).getTextContent().strip());
            }
        }
        return studentMap;
    }

    public HashMap<String, Cours> getCoursMap() {
        return coursMap;
    }

    public HashMap<String, Program> getProgramMap() {
        return programMap;
    }

    public HashMap<String, Student> getStudentMap() {
        return studentMap;
    }
}
