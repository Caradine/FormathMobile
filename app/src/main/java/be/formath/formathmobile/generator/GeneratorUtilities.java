package be.formath.formathmobile.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import be.formath.formathmobile.model.Operation;
import be.formath.generator.ProblemGenerator;
import be.formath.generator.api.Problem;

public class GeneratorUtilities {
    private static String catLevelOperationCodeTab[][][] =
            {
                    { // Category 1
                            {"A01","A02","A03","A04"}, // Level 1
                            {"M01","S01","S02"}, // Level 2
                            {"A05","A06","A07","M02"}, // Level 3
                            {"A08","A09","A10","A16","S03"}, // Level 4
                            {"D01","M03","S04","S11"}, // Level 5
                            {"A11","A12","D02","S09","S12"} // Level 6
                    },
                    { // Category 2
                            {"A01","A02","A03","A04","A05","A06","A07","A08","A09","A10","A16","M01","M02","S01","S02","S03"}, // Level 1
                            {"A17","M03","M04","S05","S10","S14"}, // Level 2
                            {"A15","A18","A19","A20","D03","D05","M05","M07","S06","S07","S08","S13","S15","S16","S17"}, // Level 3
                            {"A21","A22","A23","D04","D06","D07","M06","M08","M15","M16","M17","S19","S20"}, // Level 4
                            {"A13","A14","A24","D08","D09","M18","M19","S18","S21","S26"}, // Level 5
                            {"A25","A26","D11","D12","M20","M21","M22","S22","S23","S24"}, // Level 6
                            {"A27","A28","M09","M10","M11","M12","M13","M14","S25"}, // Level 7
                    },
                    { // Category 3
                            {"A13","A14","A15","A17","A18","A19","A20","A21","A22","A23","A24","D03","D04","D05","D06","D07","D08","D09","M03","M04","M05","M06","M07","M08","M15","M16","M17","M18","M19","S05","S06","S07","S08","S10","S13","S14","S15","S16","S17","S18","S19","S20","S21","S26"}, // Level 1
                            {"A27","A29","A30","A31","S27"}, // Level 2
                            {"D08","D09","D10","D13","S28"}, // Level 3
                            {"D21","M08","M09","M10","M11","M12","M27","S29"}, // Level 4
                            {"D02","D23","D30","M55","M56","M57","M58","M65","M66","S30"}, // Level 5
                            {"D26","D27","D28","M23","M24","M25","M26","M36"}, // Level 6
                            {"M28","M29","M32","M48","M49","M61","M67","M68","M70","M71"}, // Level 7
                    },
                    { // Category 4 (expert)
                            {"A27","A29","A30","A31","D02","D08","D09","D10","D13","D21","D23","D30","M08","M09","M10","M11","M12","M27","M55","M56","M57","M58","M65","M66","S27","S28","S29","S30"}, // Level 1
                            {"D18","D20","M25","M26","M28","M29","M30","M69","M71","M72"}, // Level 2
                            {"M31","M32","M37","M38","M44","M45","M47","M50","M52","M53","M54","M59","M60","M62"}, // Level 3
                            {"D14","D15","D16","D17","D19","D22","D24","D25","M39","M40","M43","M51","M64"}, // Level 4
                            {"D29","D31","M33","M34","M35","M41","M42","M46","M63"} // Level 5
                    }
            };

    public static ArrayList<Operation> generate_problem_list(int category, int level) {
        ArrayList<String> base_code_list = new ArrayList(Arrays.asList(catLevelOperationCodeTab[category-1][level-1]));
        ArrayList temp_code_list = new ArrayList(base_code_list);
        ArrayList<Operation> problem_list = new ArrayList<>();
        Random random = new Random();
        ProblemGenerator generator = new ProblemGenerator();
        for(int i = 0; i < 10; i++){
            if (temp_code_list.size() == 0) {
                temp_code_list = new ArrayList(base_code_list);
            }
            int choosen_index = random.nextInt(temp_code_list.size());
            String code = (String)temp_code_list.get(choosen_index);
            Problem prob = generator.getProblem(code);
            Operation oper = new Operation();
            oper.setCode(code);
            oper.setLabel(prob.getText());
            oper.setResponse(prob.getResult());
            problem_list.add(oper);
            temp_code_list.remove(choosen_index);
        }
        return problem_list;
    }
}
