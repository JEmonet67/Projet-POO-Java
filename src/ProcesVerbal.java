package projet;

import projet.Bloc;
import projet.Cours;
import projet.Program;
import projet.XmlReader;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class ProcesVerbal {
    private Program program;
    private HashMap<String, Cours> coursMap;

    public ProcesVerbal(Program program, HashMap<String, Cours> coursMap) {
        this.program = program;
        this.coursMap = coursMap;
    }

    private String getFirstLine(HashMap<String,List<Double>> unitsNotes){
        StringBuilder builder = new StringBuilder();
        builder.append("N°Etudiant ;Nom ;Prénom ;");
        builder.append(program).append(" ;");
        unitsNotes.put(program.getCode(), new ArrayList<>());

        for (Bloc bloc : program.getBlocs()){
            builder.append(bloc).append(" ;");
            unitsNotes.put(bloc.getCode(), new ArrayList<>());
            if (bloc.getCoursIds().size()>1){
                for (String coursId : bloc.getCoursIds()){
                    builder.append(coursMap.get(coursId)).append(" ;");
                    unitsNotes.put(coursId, new ArrayList<>());
                }
            }
        }
        return builder.toString();
    }

    private String studentLine(Student student, HashMap<String,List<Double>> unitsNotes){
        StringBuilder builder = new StringBuilder();
        builder.append(student.getId()).append(" ;");
        builder.append(student.getSurname()).append(" ;");
        builder.append(student.getName()).append(" ;");
        double moyenne=program.getMoyenne(student);
        builder.append(Math.round(moyenne * 1000.0) / 1000.0).append(" ;");
        unitsNotes.get(program.getCode()).add(moyenne);

        for (Bloc bloc : program.getBlocs()){
            double blocGrade = bloc.getMoyenne(student);
            if (blocGrade < 0){
                builder.append("ABI ;");
            }else{
                builder.append(Math.round(blocGrade * 1000.0) / 1000.0).append(" ;");
                unitsNotes.get(bloc.getCode()).add(blocGrade);
            }
            if (bloc.getCoursIds().size()>1){
                for (String coursId : bloc.getCoursIds()){
                    if (student.getGrades().containsKey(coursId)){
                        double coursGrade = student.getGrades().get(coursId) ;
                        if (coursGrade < 0){
                            builder.append("ABI ;");
                        }else{
                            builder.append(Math.round(coursGrade * 1000.0) / 1000.0).append(" ;");
                            unitsNotes.get(coursId).add(coursGrade);
                        }

                    }else{
                        builder.append(" ;");
                    }
                }
            }
        }
        return builder.toString();
    }

    private String statLine(HashMap<String,List<Double>> unitsNotes, Stat stat){
        StringBuilder builder = new StringBuilder();
        builder.append(stat.getLineName()).append(" ; ; ;");

        double moyenneValue = stat.roundValue(unitsNotes.get(program.getCode()));
        builder.append(moyenneValue).append(" ;");

        for (Bloc bloc : program.getBlocs()){
            double blocValue = stat.roundValue(unitsNotes.get(bloc.getCode()));
            builder.append(blocValue).append(" ;");

            if (bloc.getCoursIds().size()>1){
                for (String coursId : bloc.getCoursIds()){
                    double coursValue= stat.roundValue(unitsNotes.get(coursId));
                    builder.append(coursValue).append(" ;");

                }
            }
        }
        return builder.toString();
    }


    public void saveProcesVerbal(String filePath){
        try{
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw =new PrintWriter(bw);


            List<Student> students = program.getStudents();
            HashMap<String,List<Double>> unitsNotes = new HashMap<>();

            pw.println(getFirstLine(unitsNotes));

            Collections.sort(students);
            for (Student student : students){
                pw.println(studentLine(student,unitsNotes));
            }

            pw.println(statLine(unitsNotes,new Max()));
            pw.println(statLine(unitsNotes,new Min()));
            pw.println(statLine(unitsNotes,new Mean()));
            pw.println(statLine(unitsNotes, new StandardDev()));

            pw.flush();
            pw.close();

            JOptionPane.showMessageDialog(null,"PV created");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"PV not created");
        }
    }
}
