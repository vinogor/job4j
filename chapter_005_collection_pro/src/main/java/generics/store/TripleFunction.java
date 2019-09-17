package generics.store;

@FunctionalInterface
interface TripleFunction<T, U, Q, R> {
    R apply(T t, U u, Q q);
}
