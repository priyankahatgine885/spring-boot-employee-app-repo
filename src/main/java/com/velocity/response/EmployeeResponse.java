package com.velocity.response;

import com.velocity.dto.EmployeeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeResponse extends BaseResponse {

	private EmployeeDto employeeDto;

    private List<EmployeeDto> employeeList;

   
    public EmployeeResponse(final EmployeeDto employeeDto) {
        super(true);
        this.employeeDto = employeeDto;
    }

   
    public EmployeeResponse(final String message, final Boolean success) {
        super(success);
        super.setMessage(message);
    }
    public EmployeeResponse(final BaseResponse baseResponse) {
        super(baseResponse);
    }
    public EmployeeResponse() {
        super(true);
    }

}
