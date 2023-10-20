package com.hireme.product.payment.mapper;

import com.hireme.product.payment.Entity.Payment;
import com.hireme.product.payment.dto.PaymentDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-20T14:15:41+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDto toDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setId( payment.getId() );
        if ( payment.getUserId() != null ) {
            paymentDto.setUserId( Long.parseLong( payment.getUserId() ) );
        }
        if ( payment.getAssignmentId() != null ) {
            paymentDto.setAssignmentId( Long.parseLong( payment.getAssignmentId() ) );
        }
        paymentDto.setSessionId( payment.getSessionId() );
        paymentDto.setTotalPrice( payment.getTotalPrice() );
        paymentDto.setDteTimeCr( payment.getDteTimeCr() );
        paymentDto.setTransactionDesc( payment.getTransactionDesc() );

        return paymentDto;
    }
}
