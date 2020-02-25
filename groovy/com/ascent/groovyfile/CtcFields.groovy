package com.ascent.groovyfile

interface CtcFields {

    Long basicCtc(Long ctc, Long min_wage);

    Long bonusCtc(Long basic);

    Long employerPfContribution(Long basic);

    Long gratuityFromCtc(Long basic);

    Long grossTotal(Long ctc, Long employer_pf, Long gratuity);

    Long employerEsiContribution(Long gross);

    Long employeePfContribution(Long basic);

    Long employeeEsiContribution(Long gross);

    Long netpay(Long gross, Long employee_pf, Long employee_esi);

    Long homeRentAllowance(Long basic, Long bonus_got, Long gross_ded, Long netpay_got, Long HRA_PERCENT);

    Long grossDed(Long employee_pf, Long employee_esi);

    Long netTakeHome(Long gross, Long gross_ded);

    Long difference(Long nettakehome, Long netpay);

    Long ptGross(Long netpay, Long gross_ded);


}