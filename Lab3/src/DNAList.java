import org.w3c.dom.Node;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class DNAList<T>{
    int arraysize =20;
    LinkedList<DNA> input1=new LinkedList<DNA> ();
    LinkedList<RNA> input2=new LinkedList<RNA> ();
    File file;
    String m;
    LinkedList<DNAList<?>> DNAandRNA = new LinkedList<>();
    int size;
    private Node head;
    private static class Node<T> {
        T data;
        Node<?> next;
        public Node(T data) {
            this.data=data;
            Node<T> next;
        }
    }
    /*public void set(int a, DNAList<?> b) {
        if(a ==0) {
            Node<?> taila = b.getTail();
            taila.next=DNAandRNA.head;
            DNAandRNA.head = b.head;
            return;
        }
        Node<?> currN = DNAandRNA.head;
        for (int i = 0; i < a - 1; i++) {
            if (currN == null) {
                throw new IllegalArgumentException("Position out of range");
            }
            currN = currN.next;
        }
        Node<?> taila = b.getTail();
        taila.next = currN.next;
        currN.next= b.head;
    }*/
    private Node<?> getTail() {
        Node<?> tail = head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }
    public void add1(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void DNAList(int a,String b) {
        arraysize=a;
        for(int o =0; o <arraysize; o++) {
            DNAandRNA.add(null);
        }
        File inputfile = new File(b);

    }
    public static String[] splite (String a) {
        String[] b = a.split("");
        return b;
    }

    public void insert(int a, String b, String c ) {
        String[] m = new String[1];
        int bn =0;
        if (c != null && c != ("")) {
            m = splite(c);
        }
        if (b.equals("DNA")) {
            DNAList<DNA> a1 = new DNAList<DNA>();
            if (c != null && c != ("")) {
                try {
                    for (String o : m) {
                        CheckDNA(o);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Value must be A, C, G or T");
                    bn++;
                }
            }
            if(bn<1) {
                for (String o : m) {
                    a1.add1(CheckDNA(o));
                }
                DNAandRNA.set(a, a1);
            }

        } else if (b.equals("RNA")) {
            DNAList<RNA> a1 = new DNAList<RNA>();
            if (c != null && c != ("")) {
                try {
                    for (String o : m) {
                        CheckRNA(o);
                    };
                } catch (IllegalArgumentException e) {
                    System.out.println("Value must be A, C, G or U");
                    bn++;
                }
            }
            if(bn<1) {
                for (String o : m) {
                    a1.add1(CheckRNA(o));
                }
                DNAandRNA.set(a, a1);
            }
        } else {
            System.out.println("Error occurred while inserting");
        }

    }
    public boolean contains(DNAList<?> a, Class<?> enumClass) {
        if(a!=null) {
            Node<?> n = a.head;
            while (n != null) {
                if (enumClass.isInstance(n.data)) {
                    return true;
                }
                n = n.next;
            }
        }
        return false;
    }

    public DNA CheckDNA(String o) {
        if(o.equals("A")) {
            return DNA.A;
        } else if(o.equals("C")) {
            return DNA.C;
        } else if(o.equals("G")) {
            return DNA.G;
        } else if(o.equals("T")) {
            return DNA.T;
        } else{
            throw new IllegalArgumentException("Wrong type");
        }
    }
    public RNA CheckRNA(String o) {
        if(o.equals("A")) {
            return RNA.A;
        } else if(o.equals("C")) {
            return RNA.C;
        } else if(o.equals("G")) {
            return RNA.G;
        } else if(o.equals("U")) {
            return RNA.U;
        } else{
            throw new IllegalArgumentException("Wrong type");
        }
    }
    /*public DNAList<?> get(int a) {
        Node curr = DNAandRNA.head;
        int index=0;
        while (curr!=null) {
            if (index == a) {
                return (DNAList<?>) curr.data;
            }
        }
        return null;
    }*/
    public void remove(int k) {
        try {
            try {
                if (DNAandRNA.get(k)!=null) {
                    DNAandRNA.set(k, null);
                    System.out.print("Sequence in position " + k + " hav been removed.");
                } else {
                    System.out.print("No sequence to remove at specified position");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.print("input out of bound");
            }
        } catch (NegativeArraySizeException d) {
            System.out.print("invalid negative number");
        }
    }
    public void printlist(DNAList<?> k) {
        DNAList.Node current = k.getHead();
        while (current != null) {
            System.out.print(current.data);
            current = current.next;
        }
    }
    Node getHead() {
        return this.head;
    }
    public void print(){
        int b =0;
        for (int aa =0; aa< DNAandRNA.size(); aa++) {
            DNAList<?> e =DNAandRNA.get(aa);
            if(e!=null) {
                System.out.print(b+ "   ");
                if (contains(e, DNA.class)==true) {
                    String n = "DNA";
                    System.out.print(n + "   ");
                } else if (contains(e, RNA.class)==true) {
                    String n = "RNA";
                    System.out.print(n + "   ");
                }
                printlist(e);
                System.out.println("");
            }
            b++;
        }
    }
    public boolean clip (int a, int b, int c) {
        if(a<0||a>=DNAandRNA.size()) {
            System.out.println("No sequence to clip at specified position");
            return false;
        } else if (DNAandRNA.get(a)==(null)) {
            System.out.println("No sequence to clip at specified position");
            return false;
        }  else if (b<0) {
            System.out.println("invalid start index");
            return false;
        }  else if (b>=getSize(DNAandRNA.get(a))){
            System.out.println("Start index is out of bounds");
            return false;
        }  else if (c>=getSize(DNAandRNA.get(a))) {
            System.out.println("End index is out of bounds");
            return false;
        }
        DNAList<?> g = DNAandRNA.get(a);
        g.remover(b,c);
        return true;
    }
    public void copy(int a, int b) {
        if (a<0||a>=DNAandRNA.size()){
            System.out.println("Invalid first input");
            return;
        }
        if (b<0||b>=DNAandRNA.size()) {
            System.out.println("Invalid second input");
            return;
        }
        if (DNAandRNA.get(a)==(null)) {
            System.out.println("No sequence to copy at specified position");
            return;
        } else if (!DNAandRNA.get(a).equals(null))  {
            DNAandRNA.set(b, DNAandRNA.get(a));
            return;
        }
    }
    public void remover(int b, int c) {
        Node<?> curr = head;
        Node<?> prev = null;
        if (b > c) {
            while (curr!=null) {
                Node<?> next = curr.next;
                curr.next = null;
                curr=next;
            };
            head=null;
            return;
        }
        int d =0;
        while(d<b && curr!=null) {
            prev=curr;
            curr = curr.next;
            d++;
        }
        while (d<c && curr !=null) {
            curr=curr.next;
            d++;
        }
        if(prev!=null) {
            prev.next=curr.next;
        } else {
            head = curr.next;
        }
    }
    public int getSize(DNAList<?> a) {
        int c = 0;
        Node<?> v = a.head;
        while (v != null) {
            c++;
            v = v.next;
        }
        return c;
    }
    public String print(int e) {
        if (e<0) {
            System.out.println("input smaller than zero");
            return ("input smaller than zero");
        } else if (e>DNAandRNA.size()-1) {
            System.out.println("input outs of bound");
            return ("input outs of bound");
        }
        if(DNAandRNA.get(e)!=null) {
            if (contains(DNAandRNA.get(e), DNA.class) ==true) {
                String n = "DNA";
                System.out.print(n + "   ");
            } else if (contains(DNAandRNA.get(e), RNA.class) ==true) {
                String n = "RNA";
                System.out.print(n + "   ");
            }
            printlist(DNAandRNA.get(e));
        } else{
            return ("No sequence to print at specified position");
        }
        System.out.println("");
        return ("");
    }
    public void transcribe (int a) {
        if(DNAandRNA.get(a)==null) {
            System.out.println("No sequence to transcribe at specified position");
            return;
        }
        if(contains(DNAandRNA.get(a), DNA.class)==true) {
            int bn = 0;
            String[] kv = new String[getSize(DNAandRNA.get(a))];
            DNAList.Node current = DNAandRNA.get(a).getHead();
            while (current != null) {
                if(bn<kv.length) {
                if((current.data).equals(DNA.A)) {
                    kv[bn]="A";
                } else if((current.data).equals(DNA.C)) {
                    kv[bn]="C";
                } else if((current.data).equals(DNA.G)) {
                    kv[bn]="G";
                } else if((current.data).equals(DNA.T)) {
                    kv[bn]="T";
                };
                }
                current = current.next;
                bn++;
            }
            for(int lm= 0; lm < kv.length; lm++) {

                if(kv[lm].equals("T")) {
                    kv[lm] = "U";
                }
            };
            if (DNAandRNA.get(a)!=null) {
                DNAandRNA.set(a, null);
            }
            DNAList<RNA> lo = new DNAList<>();
            for(int j = kv.length-1; j>=0; j-- ) {
                lo.add1(CheckRNA(kv[j]));
            };
            DNAandRNA.add(a, lo);
            return;
        } else if(contains(DNAandRNA.get(a), RNA.class)==true) {
            System.out.println("Cannot transcribe RNA");
            return;
        }
    }
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        DNAList k = new DNAList();
        String zv = t.nextLine();
        String[] ti =zv.split(" ");
        if(ti[0].equals("java")) {
            if(ti[1].equals("DNAList")) {
                int ops = Integer.parseInt(ti[2]);
                String ir = ti[3];
                k.DNAList(ops, ir);
                try {
                    File kl = new File("src/"+ ir);
                    t = new Scanner(kl);
                } catch(FileNotFoundException e) {
                    System.out.println("not found");
                }
            }
        }

        while (t.hasNextLine()) {
            String l = t.nextLine();
            String[] km =l.split(" ");
            if(km[0].equals("insert")) {
                int a = Integer.parseInt(km[1]);
                String b = km[2];
                String c =km[3];
                k.insert(a,b,c);
            } else if (km[0].equals("remove")) {
                int la =Integer.parseInt(km[1]);
                k.remove(la);
                System.out.println("");
            } else if(km[0].equals("print")) {
                if(km.length < 2) {
                    k.print();
                } else if (km.length>1) {
                    int vk = Integer.parseInt(km[1]);
                    k.print(vk);
                }
            } else if(km[0].equals("clip")) {
                int qwe = Integer.parseInt(km[1]);
                int rty = Integer.parseInt(km[2]);
                int uio = Integer.parseInt(km[3]);
                k.clip(qwe, rty, uio);
            } else if(km[0].equals("copy")) {
                int asd = Integer.parseInt(km[1]);
                int fgh = Integer.parseInt(km[2]);
                k.copy(asd, fgh);
            } else if (km[0].equals("transcribe")) {
                int jkl = Integer.parseInt(km[1]);
                k.transcribe(jkl);
            }
        }

    }
}
