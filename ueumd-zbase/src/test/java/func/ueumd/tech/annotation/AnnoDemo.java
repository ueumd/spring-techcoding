package func.ueumd.tech.annotation;

@Anno(value = "this is AnnoDemo", city = "Beijing", code = "010")
public class AnnoDemo {
    @Anno(value = "this is AnnoDemo", city = "Hefei", code = "0551")
    public void output() {
        System.out.println("output something!");
    }
}
