package entryvalidator.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Customer {
    private int age;
    private double height;
    private boolean accompanied;
    private Set<ExtraInfo> extraInfo;
}
