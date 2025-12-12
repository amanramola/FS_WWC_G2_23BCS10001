import java.util.*;
class Student{
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }
    public int getMarks(){
        return marks;
    }

    public String getRole(){
        return "undergraduate";
    }

    @Override
    public String toString(){
        return id + " " + name + " ( " + marks+ " ) " + getRole();
    }
}

class GraduateStudent extends Student{
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getRole(){
        return "graduate in " + area;
    }
}

class Repository<T>{ //generic class
    private Map<String , T> data = new HashMap<>();
    public void save(String id, T obj){
        data.put(id, obj);
    }

    public T findById(String id){
        return data.get(id);
    }
    public void delete(String id){
        data.remove(id);
    }

    public void report(){
        for(Map.Entry<String, T> entry : data.entrySet()){
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}

class Teacher extends Student{
    private String subject;

    public Teacher(String id, String name, int marks, String subject) {
        super(id, name, marks);
        this.subject = subject;
    }

    @Override
    public String getRole(){
        return "teacher of " + subject;
    }
}
class main {
    public static void main(String [] args){
        List<Student> list = new ArrayList<>();
        list.add(new Student("S101", "Alice", 65));
        list.add(new Student("S102", "Alok", 95));
        list.add(new Student("S103", "Arvind", 75));
        list.add(new GraduateStudent("G201", "Bob", 90, "Computer Science"));
        list.add(new GraduateStudent("G202", "Bella", 88, "Mathematics"));

        Repository<Student> repo = new Repository<>();
        for(Student s : list){  
            repo.save(s.getId(), s);
        }

        System.out.println("All Students:");
        // for(Student s : list){
        //     System.out.println(s);
        // }
        list.forEach(System.out::println);

        System.out.println("\nLOOKUP S102:");
        Student s = repo.findById("S102");
        System.out.println(s != null ? s : "NOT FOUND");

        Iterator<Student> it = list.iterator();
        while(it.hasNext()){
            Student st = it.next();
            if(st.getMarks() < 80){
                it.remove();
                repo.delete(st.getId());
            }
        }
        System.out.println("\nAfter Removal:");
        list.forEach(System.out::println);
}
}