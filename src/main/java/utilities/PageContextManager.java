package utilities;

public class PageContextManager {
	
	private static Class<?> currentPage;

    public static void setCurrentPage(Class<?> page) {
        currentPage = page;
    }

    public static Class<?> getPage() {
        return currentPage;
    }

}
