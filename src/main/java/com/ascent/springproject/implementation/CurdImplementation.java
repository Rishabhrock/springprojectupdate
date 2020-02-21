package com.ascent.springproject.implementation;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.model.BranchDto;
import com.ascent.springproject.model.CtcDto;
import com.ascent.springproject.model.CtcDtoData;
import com.ascent.springproject.repository.BranchRepository;
import com.ascent.springproject.repository.CtcRepository;
import com.ascent.springproject.service.DomainImplementation;
import com.ascent.springproject.service.RabbitMQSender;
import com.sun.istack.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CurdImplementation implements Curd {

Logger logger = Logger.getLogger(CurdImplementation.class);


    @Autowired
    CtcRepository ctcRepository;
    @Autowired
    CtcDto ctcDto;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DomainImplementation domainImplementation;
    @Autowired
    RabbitMQSender rabbitMQSender;
   @Autowired
   CtcDtoData ctcDtoData;



    @Override
    public CtcDto newUser(CtcDto ctcDto) throws UserAlreadyExits {


        if (ctcRepository.existsById(ctcDto.getEcode())) {
            logger.info("user already exit please provide other ecode");
            throw new UserAlreadyExits("Employee with this Ecode already existed");
        } else {

          //  ctcRepository.save(ctcDto);
            rabbitMQSender.send(ctcDto);
            logger.info("user created with "+ctcDto);
            return ctcRepository.save(ctcDto);
        }

    }


    @Override
    public CtcDto ctc_page(CtcDto ctcDto) throws UserNotRegistered {

        String ename = ctcDto.getEname();
        String ecode = ctcDto.getEcode();
        System.out.println(ename);
        System.out.println(ecode);

        Long ctc = ctcDto.getCtc();
        if (ctcRepository.existsById(ecode)) {
          String state = ctcDto.getState();

            BranchDto branchDto = branchRepository.findById(state).orElse(null);


            assert branchDto != null;
            Long basic_ctc = domainImplementation.basicCtc(ctc, branchDto.getMinimum_wages());
            Long bonus_ctc = domainImplementation.bonusCtc(basic_ctc);
            Long employer_pf_contribution = domainImplementation.employerPfContribution(basic_ctc);
            Long gratuity_from_ctc = domainImplementation.gratuityFromCtc(basic_ctc);
            Long grossTotal = domainImplementation.grossTotal(ctc, employer_pf_contribution, gratuity_from_ctc);
            Long employer_esi_contribution = domainImplementation.employerEsiContribution(grossTotal);
            Long employee_pf_contribution = domainImplementation.employeePfContribution(basic_ctc);
            Long employee_esi_contribution = domainImplementation.employeeEsiContribution(grossTotal);
            Long netpay = domainImplementation.netpay(grossTotal, employee_pf_contribution, employee_esi_contribution);
            Long grossDed = domainImplementation.grossDed(employee_pf_contribution, employee_esi_contribution);
            Long nettakehome = domainImplementation.netTakeHome(grossTotal, grossDed);
            Long difference = domainImplementation.difference(nettakehome, netpay);
            Long ptgross = domainImplementation.ptGross(netpay, grossDed);
            Long homerentallowance = domainImplementation.homeRentAllowance(basic_ctc, bonus_ctc, grossDed, netpay, branchDto.getHra_per());

       //   ctcDto = new CtcDto(ecode,ename,state,state,homerentallowance,nettakehome,ctc,basic_ctc,bonus_ctc, 0L,employer_pf_contribution,employer_esi_contribution,gratuity_from_ctc,grossTotal,employee_pf_contribution,employee_esi_contribution, 0L, 0L,grossDed,difference,ptgross,netpay);
            ctcDtoData = new CtcDtoData(state,state,homerentallowance,nettakehome,ctc,basic_ctc,bonus_ctc,0L,employer_pf_contribution,employer_esi_contribution,gratuity_from_ctc,grossTotal,employee_pf_contribution,employee_esi_contribution, 0L, 0L,grossDed,difference,ptgross,netpay);
            ctcDto.getMap().put(ctcDto.getEcode(),ctcDtoData);
            ctcRepository.save(ctcDto);
            rabbitMQSender.send(ctcRepository.getOne(ecode));
            return ctcRepository.getOne(ecode);

        } else {
            throw new UserNotRegistered("User is not Registed in portal");
       }
    }

    @Override
    public CtcDto findUserDetail(String ecode) throws UserNotRegistered {                                      //check...

        if (ctcRepository.existsById(ecode)) {
            ctcDto.setEcode(ecode);

            rabbitMQSender.send(ctcRepository.findByEcode(ecode));

            return ctcRepository.findByEcode(ecode);
            //previously i was using  T getOne(ID var1); now changed to custom query
        } else {
            throw new UserNotRegistered("User is not Registed in portal");
        }
    }

    @Override
    public CtcDto updateUser(String ecode, CtcDto ctcDto) {
        return (ctcRepository.findById(ecode)
                .map(dataBaseImplementaion1 -> {
                    dataBaseImplementaion1.setEname(ctcDto.getEname());
                    //employee.setRole(newEmployee.getRole());
                    rabbitMQSender.send(ctcRepository.save(dataBaseImplementaion1));

                    return ctcRepository.save(dataBaseImplementaion1);
                }).orElse(null)

        );
    }

    @Override
    public String deleteUser(String ecode) throws UserNotRegistered {

        if (ctcRepository.existsById(ecode)) {
            CtcDto a = ctcRepository.getOne(ecode);
            ctcRepository.delete(a);
            rabbitMQSender.sendDeleteMessage("Employee Record is Deleted");
            return "Employee deleted";
        } else {
            throw new UserNotRegistered("User is not Registed in portal");
        }
    }


}
