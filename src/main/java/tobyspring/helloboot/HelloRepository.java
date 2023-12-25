package tobyspring.helloboot;

public interface HelloRepository {
    Hello findHello(String name);
    void increaseCount(String name);

    // default method 적절한 사용 예시는 java.util.Comparator 인터페이스를 참고하자.
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
