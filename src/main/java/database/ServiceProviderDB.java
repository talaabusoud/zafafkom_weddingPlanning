package database;

import entity.ServiceProvider;
import main.LoggerUtility ;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ServiceProviderDB {
    private static Logger logger = LoggerUtility.getLogger();

    static List<ServiceProvider> serviceProviders = new ArrayList<>();
    static ServiceProvider serviceProvider = new ServiceProvider();

    private ServiceProviderDB() {
        throw new IllegalStateException("Utility class");
    }

    static {
        serviceProviders.add(new ServiceProvider("123", "mo@gmail", "0655433", "hebron", "mohamad", 1));
        serviceProviders.add(new ServiceProvider("000", "ta@gmail", "098866", "nablus", "tala", 2));
    }

    public static void addServiceProvider(ServiceProvider serviceProvider) {
        serviceProviders.add(serviceProvider);
    }

    public static List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public static void displayServiceProvider(ServiceProvider serviceProvider) {
        if (serviceProvider == null) {
            logger.warning("This service provider is not exist\n");
        } else {
            // تحديد الصيغة المناسبة لطباعة معلومات مقدم الخدمة
            String format = "|%-12s|%12s|%15s|%14s|%20s|%20s|\n";

            // طباعة العناوين لكل عمود
            logger.info(String.format(format, "ID", "Name", "Phone", "Address", "Email", "password"));

            // طباعة معلومات مقدم الخدمة الفعلية استخدام المتغير serviceProvider للوصول إلى خصائص الكائن
            logger.info(String.format(format,
                    serviceProvider.getId(),
                    serviceProvider.getName(),
                    serviceProvider.getPhone(),
                    serviceProvider.getAddress(),
                    serviceProvider.getEmail(),
                    serviceProvider.getPassword() // تأكد من أنه من المقبول عرض كلمة المرور هنا
            ));
        }
    }

    public static void displayServiceProviders(List<ServiceProvider> serviceProviders) {
        logger.info("----------------------------------Service Providers----------------------------\n");
        logger.info("|     id     |    Name    |    Phone   |    Address   |    Email   |\n");

        for (ServiceProvider sp : serviceProviders) {
            displayServiceProvider(sp);
        }

    }
}
