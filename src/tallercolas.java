import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class tallercolas extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JButton verHistorialButton;
    private JButton eliminarHistorialButton;
    private JButton MOSTRARDATOSButton;
    private JTextArea mostar;
    private JTextField id1;
    private JTextField cedula;
    private JTextField tiempo;
    private JButton INSERTARButton;
    private JButton insertarPreDefinidosButton;
    private JButton MOSTRARButton;
    private JTextField valor;
    private JButton CAMBIARButton;
    private JTextArea procesamiento;
    private JTextArea mostardatos;
    private JLabel idLabel;
    private JButton roundRobinButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton verUlimoProcesoButton;
    private JButton buttonOK;

    private int cuanto=20;


    Queue<Procesos> procesoscola = new LinkedList<>();
    Stack<Procesos> historial =new Stack<>();




    public tallercolas() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //INSERTAR PREDEFINIDOS
        insertarPreDefinidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarpredef();
                mostrarDatos();
               // mostardatos.setText("Los procesos son:\n " + procesoscola);
                System.out.println(procesoscola);

            }
        });

        //INSERTAR UN NUEVO PROCESO
        INSERTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesoscola.add(new Procesos(id1.getText(),Integer.parseInt(cedula.getText()) ,Integer.parseInt(tiempo.getText())));
                mostrarDatos();
               // mostardatos.setText("Los procesos son:\n " + procesoscola);
                System.out.println(procesoscola);
            }
        });

        MOSTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostar.setText("-DATOS DE ESTUDIANTES-\nNombres: Camila Vega\tKristiany Cerón \nCédula: 1721436507\tCédula: 1726349580\n Id Banner:A00081787 \tId Banner:A00087627\n");
               // mostardatos.setText("Los procesos son:\n " + procesoscola);
            }
        });
        CAMBIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuanto=Integer.parseInt(valor.getText());
                //roundRobin();
            }
        });
        roundRobinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundRobin();
            }
        });
        MOSTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eliminarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarHistorial();
            }
        });
        verHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatoshistorial();
            }
        });
        verUlimoProcesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText( historial.peek().toString());
            }
        });
    }

    private void insertarpredef(){
        Procesos proceso1=new Procesos("P 1",1002856059,100);
        Procesos proceso2=new Procesos("P 2",1714196743,15);
        Procesos proceso3=new Procesos("P 3",1456756888,40);
        Procesos proceso4=new Procesos("P 4",1234556584,30);
        Procesos proceso5=new Procesos("P 5",1323567987,18);
        Procesos proceso6=new Procesos("P 6",1454536584,45);
        procesoscola.add(proceso1);
        procesoscola.add(proceso2);
        procesoscola.add(proceso3);
        procesoscola.add(proceso4);
        procesoscola.add(proceso5);
        procesoscola.add(proceso6);
    }

    private void roundRobin(){
        Procesos pc=new Procesos("",0,0);
        int totalp=0;//tiempo calculando con el cuanto
        int tiempo1=0;//tiempo desde 0
        int Conmutaciones=0;
        if (procesoscola.size()>1) {
            while (procesoscola.size() > 1) {

            pc = procesoscola.poll();
            System.out.println("Tiempo: " + tiempo1 + ": " + pc.getId() + " entra en ejecución");
            procesamiento.append("Tiempo: " + tiempo1 + ": " + pc.getId() + " entra en ejecución\n");
            totalp = pc.getTiempo() - cuanto;

            if (totalp <= 0) {//FINALIZA
                tiempo1 = tiempo1 + pc.getTiempo();
                System.out.println("Tiempo " + tiempo1 + ":" + pc.getId() + " finaliza ejecución");
                procesamiento.append("Tiempo " + tiempo1 + ":" + pc.getId() + " finaliza ejecución\n");
                historial.push(pc);

            } else {
                tiempo1 = tiempo1 + cuanto;
                Conmutaciones++;
                System.out.println("Tiempo " + tiempo1 + ":" + pc.getId() + " se conmuta. Pendiente por ejecutar " + totalp);
                procesamiento.append("Tiempo " + tiempo1 + ":" + pc.getId() + " se conmuta. Pendiente por ejecutar " + totalp + "\n");
                pc.setTiempo(totalp);
                procesoscola.offer(pc);//añade otra vez a la cola
            }
        }
        }
        if(procesoscola.size()==1){
            while(procesoscola.size()>0){
                pc=procesoscola.poll();
                System.out.println("Tiempo: "+ tiempo1+": "+pc.getId()+" entra en ejecución");
                procesamiento.append("Tiempo: "+ tiempo1+": "+pc.getId()+" entra en ejecución\n");
                totalp=pc.getTiempo()-cuanto;

                if(totalp<=0){
                    tiempo1=tiempo1+pc.getTiempo();
                    System.out.println("Tiempo "+tiempo1+":"+pc.getId()+" finaliza ejecución");
                    procesamiento.append("Tiempo "+tiempo1+":"+pc.getId()+" finaliza ejecución\n");
                    historial.push(pc);
                }else{
                    tiempo1=tiempo1+cuanto;
                    Conmutaciones++;
                    System.out.println("Tiempo "+tiempo1+":"+pc.getId()+" continua ejecutandose... ");
                    procesamiento.append("Tiempo "+tiempo1+":"+pc.getId()+" continua ejecutandose...\n");
                    pc.setTiempo(totalp);
                    procesoscola.offer(pc);
                }
            }

        }
        System.out.println("--ESTADÍSTICAS GENERADAS--");
        procesamiento.append("ESTADÍSTICAS GENERADAS\n");
        System.out.println("Total tiempo de ejecución de todos los procesos: "+tiempo1+"ms");
        procesamiento.append("Total tiempo de ejecución de todos los procesos: "+tiempo1+"ms\n");
        System.out.println("Total de conmutaciones:"+Conmutaciones);
        procesamiento.append("Total de conmutaciones:"+Conmutaciones+"\n");
    }

    private void mostrarDatos(){
        String cedula,tiempo;
        String mostrar=" ";
        for (Procesos p:procesoscola) {
           cedula=String.valueOf(p.getCedula());
           tiempo=String.valueOf(p.getTiempo());
           mostrar=mostrar+ "\n"+p.getId()+", "+cedula+ ", "+tiempo;
        }
        mostardatos.setText(mostrar);
        textArea2.setText(mostrar);
    }
    private void mostrarDatoshistorial(){
        String cedula,tiempo;
        String mostrar=" ";
        for (Procesos h:historial) {
            cedula=String.valueOf(h.getCedula());
            tiempo=String.valueOf(h.getTiempo());
            mostrar=mostrar+ "\n"+h.getId()+", "+cedula+ ", "+tiempo;
        }
        textArea1.setText(mostrar);
    }

    private void borrarHistorial() {
        procesoscola.clear();
        textArea1.setText(" ");
    }

    public static void main(String[] args) {
        tallercolas dialog = new tallercolas();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
