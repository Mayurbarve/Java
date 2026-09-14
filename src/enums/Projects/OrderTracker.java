package enums.Projects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum OrderStatus {
    PLACED,
    PACKED,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    List<OrderStatus> allowedNext() {
        switch (this) {
            case PLACED:
                return Arrays.asList(PACKED, CANCELLED);

            case PACKED:
                return Arrays.asList(SHIPPED, CANCELLED);

            case SHIPPED:
                return Arrays.asList(DELIVERED);

            case DELIVERED:
            case CANCELLED:
                return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    boolean canAdvanceTo(OrderStatus target) {
        return allowedNext().contains(target);
    }

    boolean isTerminal() {
        return allowedNext().isEmpty();
    }

    static OrderStatus fromName(String name) {
        for (OrderStatus status : values()) {
            if (status.name().equals(name)) {
                return status;
            }
        }

        return null;
    }
}

class OrderTracker {

    private OrderStatus current = OrderStatus.PLACED;
    private int steps = 0;

    public OrderTracker() {
    }

    public String status() {
        return current.name();
    }

    public boolean advanceTo(String target) {
        OrderStatus next = OrderStatus.fromName(target);

        if (next == null || !current.canAdvanceTo(next)) {
            return false;
        }

        current = next;
        steps++;

        return true;
    }

    public String[] allowedNext() {
        List<OrderStatus> next = current.allowedNext();

        String[] out = new String[next.size()];

        for (int i = 0; i < next.size(); i++) {
            out[i] = next.get(i).name();
        }

        return out;
    }

    public boolean isTerminal() {
        return current.isTerminal();
    }

    public int stepCount() {
        return steps;
    }
}