package com.ascent.springproject.implementation;

import com.ascent.springproject.exception.UserAlreadyExits;
import com.ascent.springproject.exception.UserNotRegistered;
import com.ascent.springproject.model.BranchDto;
import com.ascent.springproject.model.CtcDto;
import com.ascent.springproject.repository.BranchRepository;
import com.ascent.springproject.repository.CtcRepository;
import com.ascent.springproject.service.DomainImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Service
public class CurdImplementation implements Curd {


    @Autowired
    CtcRepository ctcRepository;
    @Autowired
    CtcDto ctcDto;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DomainImplementation domainImplementation;


    @Override
    public CtcDto newUser(String Ename, String Ecode) throws UserAlreadyExits {

        if (ctcRepository.existsById(Ecode)) {
            throw new UserAlreadyExits("Employee with this Ecode already existed");
        } else {
            CtcDto ctcDto = new CtcDto();
            ctcDto.setEname(Ename);
            ctcDto.setEcode(Ecode);
            ctcRepository.save(ctcDto);
            return ctcDto;
        }
    }


    @Override
    public CtcDto ctc_page(Long ctc, String state, String ecode) throws UserNotRegistered {

        if (ctcRepository.existsById(ecode)) {

            ModelAndView mv = new ModelAndView();
            System.out.println("1");
            //  Long ctc = Long.parseLong(ctc_got);
            System.out.println("2");
            ctcDto = ctcRepository.findById(ecode).orElse(null);
            System.out.println("2.1");
            assert ctcDto != null;
            String ename = ctcDto.getEname();  // changes
            System.out.println(ename);
            ctcDto.setEname(ename);
            ctcDto.setCtc(ctc);
            ctcDto.setLoc(state);
            ctcDto.setState(state);
            System.out.println("3");
            BranchDto branchDto = branchRepository.findById(state).orElse(null);


            System.out.println(branchDto);
            System.out.println("4");
            assert branchDto != null;
            Long basic_ctc = domainImplementation.basic_ctc(ctc, branchDto.getMinimum_wages());
            Long bonus_ctc = domainImplementation.bonus_ctc(basic_ctc);
            Long employer_pf_contribution = domainImplementation.employer_pf_contribution(basic_ctc);
            Long gratuity_from_ctc = domainImplementation.gratuity_from_ctc(basic_ctc);
            Long grossTotal = domainImplementation.gross_total(ctc, employer_pf_contribution, gratuity_from_ctc);
            Long employer_esi_contribution = domainImplementation.employer_esi_contribution(grossTotal);
            Long employee_pf_contribution = domainImplementation.employee_pf_contribution(basic_ctc);
            Long employee_esi_contribution = domainImplementation.employee_esi_contribution(grossTotal);
            Long netpay = domainImplementation.netpay(grossTotal, employee_pf_contribution, employee_esi_contribution);
            Long grossDed = domainImplementation.grossDed(employee_pf_contribution, employee_esi_contribution);
            Long nettakehome = domainImplementation.nettakehome(grossTotal, grossDed);
            Long difference = domainImplementation.difference(nettakehome, netpay);
            Long ptgross = domainImplementation.ptgross(netpay, grossDed);
            Long homerentallowance = domainImplementation.homerentallowance(basic_ctc, bonus_ctc, grossDed, netpay, branchDto.getHra_per());

            System.out.println("5");

            ctcDto.setBasic(basic_ctc);
            ctcDto.setBonus(bonus_ctc);
            ctcDto.setEmployer_pf(employer_pf_contribution);
            ctcDto.setGratuity(gratuity_from_ctc);
            ctcDto.setGross(grossTotal);
            ctcDto.setEmployer_esi(employer_esi_contribution);
            ctcDto.setEmployee_Pf(employee_pf_contribution);
            ctcDto.setEmployee_esi(employee_esi_contribution);
            ctcDto.setNet_Pay(netpay);
            ctcDto.setGross_ded(grossDed);
            ctcDto.setNet_take_home(nettakehome);
            ctcDto.setDiff(difference);
            ctcDto.setPt_gross(ptgross);
            ctcDto.setHra(homerentallowance);
            ctcDto.setEcode(ecode);

            System.out.println("6");

            ctcRepository.save(ctcDto);

            System.out.println(basic_ctc);
            System.out.println(bonus_ctc);

            System.out.println("7");

            mv.addObject(ctcDto);
            System.out.println("8");
            mv.setViewName("ctc_detail");
            System.out.println("9");
            return ctcRepository.getOne(ecode);

        } else {
            // System.out.println("inside ctc");
            throw new UserNotRegistered("User is not Registed in portal");
//        return null;
        }
    }

    @Override
    public CtcDto findUserDetail(String ecode) throws UserNotRegistered {                                      //check...

        if (ctcRepository.existsById(ecode)) {

            System.out.println("inside findUserDetails");
//        dataBaseRepo.findById(ecode);
            CtcDto ctcDto = ctcRepository.getOne(ecode);
            return ctcDto;
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
                    return ctcRepository.save(dataBaseImplementaion1);
                })
                .orElseGet(() -> {

                    ctcDto.setEcode(ecode);
                    return ctcRepository.save(ctcDto);
                }));
    }

    @Override
    public String deleteUser(String ecode) throws UserNotRegistered {

        if (ctcRepository.existsById(ecode)) {
            System.out.println("inside delete");
            CtcDto a = ctcRepository.getOne(ecode);
            ctcRepository.delete(a);
            return "Employee deleted";
        } else {
            throw new UserNotRegistered("User is not Registed in portal");
        }
    }


}
