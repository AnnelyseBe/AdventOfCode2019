package be.annelyse.year2019;

import be.annelyse.util.Transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//todo debug, nog een fout in, want day 11 kon ik niet oplossen met mijn computer (met die van pieter gedaan)

/*output n° 30: 1...............................................
        0:3 1:8 2:1005 3:8 4:304 5:1106 6:0 7:11 8:0 9:1 10:1 11:104 12:1 13:104 14:0 15:3 16:8 17:102 18:-1 19:8 20:10 21:101 22:1 23:10 24:10 25:4 26:10 27:1008 28:8 29:1 30:10 31:4 32:10 33:1002 34:8 35:1 36:29 37:2 38:103 39:1 40:10 41:1 42:106 43:18 44:10 45:3 46:8 47:102 48:-1 49:8 50:10 51:1001 52:10 53:1 54:10 55:4 56:10 57:1008 58:8 59:0 60:10 61:4 62:10 63:102 64:1 65:8 66:59 67:2 68:102 69:3 70:10 71:2 72:1101 73:12 74:10 75:3 76:8 77:102 78:-1 79:8 80:10 81:1001 82:10 83:1 84:10 85:4 86:10 87:108 88:0 89:8 90:10 91:4 92:10 93:101 94:0 95:8 96:88 97:3 98:8 99:102 100:-1 101:8 102:10 103:1001 104:10 105:1 106:10 107:4 108:10 109:108 110:1 111:8 112:10 113:4 114:10 115:101 116:0 117:8 118:110 119:2 120:108 121:9 122:10 123:1006 124:0 125:56 126:3 127:8 128:102 129:-1 130:8 131:10 132:1001 133:10 134:1 135:10 136:4 137:10 138:108 139:0 140:8 141:10 142:4 143:10 144:101 145:0 146:8 147:139 148:1 149:108 150:20 151:10 152:3 153:8 154:102 155:-1 156:8 157:10 158:101 159:1 160:10 161:10 162:4 163:10 164:108 165:0 166:8 167:10 168:4 169:10 170:102 171:1 172:8 173:165 174:1 175:104 176:9 177:10 178:3 179:8 180:102 181:-1 182:8 183:10 184:101 185:1 186:10 187:10 188:4 189:10 190:1008 191:8 192:0 193:10 194:4 195:10 196:1001 197:8 198:0 199:192 200:2 201:9 202:14 203:10 204:2 205:1103 206:5 207:10 208:1 209:1108 210:5 211:10 212:3 213:8 214:1002 215:8 216:-1 217:10 218:101 219:1 220:10 221:10 222:4 223:10 224:1008 225:8 226:1 227:10 228:4 229:10 230:102 231:1 232:8 233:226 234:1006 235:0 236:73 237:1006 238:0 239:20 240:1 241:1106 242:11 243:10 244:1 245:1105 246:7 247:10 248:3 249:8 250:102 251:-1 252:8 253:10 254:1001 255:10 256:1 257:10 258:4 259:10 260:108 261:1 262:8 263:10 264:4 265:10 266:1001 267:8 268:0 269:261 270:3 271:8 272:102 273:-1 274:8 275:10 276:101 277:1 278:10 279:10 280:4 281:10 282:108 283:0 284:8 285:10 286:4 287:10 288:1002 289:8 290:1 291:283 292:101 293:1 294:9 295:9 296:1007 297:9 298:1052 299:10 300:1005 301:10 302:15 303:99 304:109 305:626 306:104 307:0 308:104 309:1 310:21101 311:48062899092 312:0 313:1 314:21101 315:0 316:321 317:0 318:1105 319:1 320:425 321:21101 322:936995300108 323:0 324:1 325:21101 326:0 327:332 328:0 329:1106 330:0 331:425 332:3 333:10 334:104 335:0 336:104 337:1 338:3 339:10 340:104 341:0 342:104 343:0 344:3 345:10 346:104 347:0 348:104 349:1 350:3 351:10 352:104 353:0 354:104 355:1 356:3 357:10 358:104 359:0 360:104 361:0 362:3 363:10 364:104 365:0 366:104 367:1 368:21102 369:209382902951 370:1 371:1 372:21101 373:379 374:0 375:0 376:1106 377:0 378:425 379:21102 380:179544747200 381:1 382:1 383:21102 384:390 385:1 386:0 387:1106 388:0 389:425 390:3 391:10 392:104 393:0 394:104 395:0 396:3 397:10 398:104 399:0 400:104 401:0 402:21102 403:1 404:709488292628 405:1 406:21102 407:1 408:413 409:0 410:1106 411:0 412:425 413:21101 414:0 415:983929868648 416:1 417:21101 418:424 419:0 420:0 421:1105 422:1 423:425 424:99 425:109 426:2 427:22101 428:0 429:-1 430:1 431:21102 432:40 433:1 434:2 435:21102 436:456 437:1 438:3 439:21101 440:446 441:0 442:0 443:1106 444:0 445:489 446:109 447:-2 448:2106 449:0 450:0 451:0 452:1 453:0 454:0 455:1 456:109 457:2 458:3 459:10 460:204 461:-1 462:1001 463:451 464:452 465:467 466:4 467:0 468:1001 469:451 470:1 471:451 472:108 473:4 474:451 475:10 476:1006 477:10 478:483 479:1102 480:0 481:1 482:451 483:109 484:-2 485:2105 486:1 487:0 488:0 489:109 490:4 491:1201 492:-1 493:0 494:488 495:1207 496:-3 497:0 498:10 499:1006 500:10 501:506 502:21102 503:1 504:0 505:-3 506:21202 507:-3 508:1 509:1 510:21201 511:-2 512:0 513:2 514:21101 515:0 516:1 517:3 518:21101 519:525 520:0 521:0 522:1105 523:1 524:530 525:109 526:-4 527:2105 528:1 529:0 530:109 531:5 532:1207 533:-3 534:1 535:10 536:1006 537:10 538:553 539:2207 540:-4 541:-2 542:10 543:1006 544:10 545:553 546:21202 547:-4 548:1 549:-4 550:1105 551:1 552:621 553:21201 554:-4 555:0 556:1 557:21201 558:-3 559:-1 560:2 561:21202 562:-2 563:2 564:3 565:21102 566:1 567:572 568:0 569:1106 570:0 571:530 572:21201 573:1 574:0 575:-4 576:21101 577:0 578:1 579:-1 580:2207 581:-4 582:-2 583:10 584:1006 585:10 586:591 587:21102 588:0 589:1 590:-1 591:22202 592:-2 593:-1 594:-2 595:2107 596:0 597:-3 598:10 599:1006 600:10 601:613 602:22101 603:0 604:-1 605:1 606:21101 607:0 608:613 609:0 610:106 611:0 612:488 613:21202 614:-2 615:-1 616:-2 617:22201 618:-4 619:-2 620:-4 621:109 622:-5 623:2106 624:0 625:0
        ***********************************************************************************************************************
        Instruction: 108 OPcode: 8 InstructionPointer: 138***********************************************************************************************************************
        0:3 1:8 2:1005 3:8 4:304 5:1106 6:0 7:11 8:0 9:1 10:1 11:104 12:1 13:104 14:0 15:3 16:8 17:102 18:-1 19:8 20:10 21:101 22:1 23:10 24:10 25:4 26:10 27:1008 28:8 29:1 30:10 31:4 32:10 33:1002 34:8 35:1 36:29 37:2 38:103 39:1 40:10 41:1 42:106 43:18 44:10 45:3 46:8 47:102 48:-1 49:8 50:10 51:1001 52:10 53:1 54:10 55:4 56:10 57:1008 58:8 59:0 60:10 61:4 62:10 63:102 64:1 65:8 66:59 67:2 68:102 69:3 70:10 71:2 72:1101 73:12 74:10 75:3 76:8 77:102 78:-1 79:8 80:10 81:1001 82:10 83:1 84:10 85:4 86:10 87:108 88:0 89:8 90:10 91:4 92:10 93:101 94:0 95:8 96:88 97:3 98:8 99:102 100:-1 101:8 102:10 103:1001 104:10 105:1 106:10 107:4 108:10 109:108 110:1 111:8 112:10 113:4 114:10 115:101 116:0 117:8 118:110 119:2 120:108 121:9 122:10 123:1006 124:0 125:56 126:3 127:8 128:102 129:-1 130:8 131:10 132:1001 133:10 134:1 135:10 136:4 137:10 138:108 139:0 140:8 141:10 142:4 143:10 144:101 145:0 146:8 147:139 148:1 149:108 150:20 151:10 152:3 153:8 154:102 155:-1 156:8 157:10 158:101 159:1 160:10 161:10 162:4 163:10 164:108 165:0 166:8 167:10 168:4 169:10 170:102 171:1 172:8 173:165 174:1 175:104 176:9 177:10 178:3 179:8 180:102 181:-1 182:8 183:10 184:101 185:1 186:10 187:10 188:4 189:10 190:1008 191:8 192:0 193:10 194:4 195:10 196:1001 197:8 198:0 199:192 200:2 201:9 202:14 203:10 204:2 205:1103 206:5 207:10 208:1 209:1108 210:5 211:10 212:3 213:8 214:1002 215:8 216:-1 217:10 218:101 219:1 220:10 221:10 222:4 223:10 224:1008 225:8 226:1 227:10 228:4 229:10 230:102 231:1 232:8 233:226 234:1006 235:0 236:73 237:1006 238:0 239:20 240:1 241:1106 242:11 243:10 244:1 245:1105 246:7 247:10 248:3 249:8 250:102 251:-1 252:8 253:10 254:1001 255:10 256:1 257:10 258:4 259:10 260:108 261:1 262:8 263:10 264:4 265:10 266:1001 267:8 268:0 269:261 270:3 271:8 272:102 273:-1 274:8 275:10 276:101 277:1 278:10 279:10 280:4 281:10 282:108 283:0 284:8 285:10 286:4 287:10 288:1002 289:8 290:1 291:283 292:101 293:1 294:9 295:9 296:1007 297:9 298:1052 299:10 300:1005 301:10 302:15 303:99 304:109 305:626 306:104 307:0 308:104 309:1 310:21101 311:48062899092 312:0 313:1 314:21101 315:0 316:321 317:0 318:1105 319:1 320:425 321:21101 322:936995300108 323:0 324:1 325:21101 326:0 327:332 328:0 329:1106 330:0 331:425 332:3 333:10 334:104 335:0 336:104 337:1 338:3 339:10 340:104 341:0 342:104 343:0 344:3 345:10 346:104 347:0 348:104 349:1 350:3 351:10 352:104 353:0 354:104 355:1 356:3 357:10 358:104 359:0 360:104 361:0 362:3 363:10 364:104 365:0 366:104 367:1 368:21102 369:209382902951 370:1 371:1 372:21101 373:379 374:0 375:0 376:1106 377:0 378:425 379:21102 380:179544747200 381:1 382:1 383:21102 384:390 385:1 386:0 387:1106 388:0 389:425 390:3 391:10 392:104 393:0 394:104 395:0 396:3 397:10 398:104 399:0 400:104 401:0 402:21102 403:1 404:709488292628 405:1 406:21102 407:1 408:413 409:0 410:1106 411:0 412:425 413:21101 414:0 415:983929868648 416:1 417:21101 418:424 419:0 420:0 421:1105 422:1 423:425 424:99 425:109 426:2 427:22101 428:0 429:-1 430:1 431:21102 432:40 433:1 434:2 435:21102 436:456 437:1 438:3 439:21101 440:446 441:0 442:0 443:1106 444:0 445:489 446:109 447:-2 448:2106 449:0 450:0 451:0 452:1 453:0 454:0 455:1 456:109 457:2 458:3 459:10 460:204 461:-1 462:1001 463:451 464:452 465:467 466:4 467:0 468:1001 469:451 470:1 471:451 472:108 473:4 474:451 475:10 476:1006 477:10 478:483 479:1102 480:0 481:1 482:451 483:109 484:-2 485:2105 486:1 487:0 488:0 489:109 490:4 491:1201 492:-1 493:0 494:488 495:1207 496:-3 497:0 498:10 499:1006 500:10 501:506 502:21102 503:1 504:0 505:-3 506:21202 507:-3 508:1 509:1 510:21201 511:-2 512:0 513:2 514:21101 515:0 516:1 517:3 518:21101 519:525 520:0 521:0 522:1105 523:1 524:530 525:109 526:-4 527:2105 528:1 529:0 530:109 531:5 532:1207 533:-3 534:1 535:10 536:1006 537:10 538:553 539:2207 540:-4 541:-2 542:10 543:1006 544:10 545:553 546:21202 547:-4 548:1 549:-4 550:1105 551:1 552:621 553:21201 554:-4 555:0 556:1 557:21201 558:-3 559:-1 560:2 561:21202 562:-2 563:2 564:3 565:21102 566:1 567:572 568:0 569:1106 570:0 571:530 572:21201 573:1 574:0 575:-4 576:21101 577:0 578:1 579:-1 580:2207 581:-4 582:-2 583:10 584:1006 585:10 586:591 587:21102 588:0 589:1 590:-1 591:22202 592:-2 593:-1 594:-2 595:2107 596:0 597:-3 598:10 599:1006 600:10 601:613 602:22101 603:0 604:-1 605:1 606:21101 607:0 608:613 609:0 610:106 611:0 612:488 613:21202 614:-2 615:-1 616:-2 617:22201 618:-4 619:-2 620:-4 621:109 622:-5 623:2106 624:0 625:0
        **************/

public class IntCodeComputer2 implements Runnable {

    //for debugging
    int outputcounter = 0;
    int inputcounter = 0;

    private List<Long> memory;
    /**
     * This list had the priority for working with position or immediate mode. For values we write we always work in (forgot, look in the function)
     * 0 = position mode (parameter interpreted as position)
     * 1 = immediate mode (parameter interpreted as value)
     * 2 = relative mode (parameter interpreted as a relative position with a relativeBase
     */
    private List<Integer> memoryMode;
    private int instructionPointer;
    private int relativeBase;
    private LinkedBlockingQueue<Long> input;
    private LinkedBlockingQueue<Long> output;

    public IntCodeComputer2(List<Long> computerSoftware) {
        this.memory = computerSoftware;
        memoryMode = memory.stream().map(value -> 9).collect(Collectors.toList());
        instructionPointer = 0;
        relativeBase = 0;
    }

    public IntCodeComputer2(List<Long> computerSoftware, LinkedBlockingQueue<Long> input, LinkedBlockingQueue<Long> output) {
        this.memory = new ArrayList<>();
        memory.addAll(computerSoftware);
        this.input = input;
        this.output = output;
        memoryMode = memory.stream().map(value -> 9).collect(Collectors.toList());
        instructionPointer = 0;
        relativeBase = 0;
    }

    @Override
    public void run() {

        while (true) {
            int opCode = getOpCode(instructionPointer);
            System.out.println("\n***********************************************************************************************************************");
            System.out.println("\n***********************************************************************************************************************");

            for (int i = 0; i < memoryMode.size(); i++) {
                System.out.print(i + ":" + memoryMode.get(i) + " ");
            }
            System.out.println("\n***********************************************************************************************************************");

            for (int i = 0; i < memory.size(); i++) {
                System.out.print(i + ":" + memory.get(i) + " ");
            }


            System.out.println(" ");
            System.out.println("***********************************************************************************************************************");
            System.out.print("Instruction: " + memory.get(instructionPointer) + " OPcode: " + opCode + " InstructionPointer: " + instructionPointer);
            System.out.println("***********************************************************************************************************************");


            switch (opCode) {
                case 1:
                    opCode1();
                    break;
                case 2:
                    opCode2();
                    break;
                case 3:
                    opCode3();
                    break;
                case 4:
                    opCode4();
                    break;
                case 5:
                    opCode5();
                    break;
                case 6:
                    opCode6();
                    break;
                case 7:
                    opCode7();
                    break;
                case 8:
                    opCode8();
                    break;
                case 9:
                    opCode9();
                    break;
                case 99:
                    return;
                default:
                    throw new RuntimeException("not a valid OptCode: " + opCode + " InstructionPointer: " + instructionPointer);
            }
        }
    }

    /**
     * adding
     */
    private void opCode1() {
        long parameter1 = getValueOfParameter(instructionPointer, 1);
        long parameter2 = getValueOfParameter(instructionPointer, 2);

        long result = parameter1 + parameter2;

        writeResult(instructionPointer, 3, result);

        instructionPointer += 4;
    }

    /**
     * multiplication
     */
    private void opCode2() {

        long parameter1 = getValueOfParameter(instructionPointer, 1);
        long parameter2 = getValueOfParameter(instructionPointer, 2);

        long result = parameter1 * parameter2;

        writeResult(instructionPointer, 3, result);
        instructionPointer += 4;
    }

    /**
     * save input
     */
    private void opCode3() {
        long result = getComputerInput();
        writeResult(instructionPointer, 1, result);
        instructionPointer += 2;
    }

    /**
     * generate output
     */
    private void opCode4() {
        long result = getValueOfParameter(instructionPointer, 1);
        System.out.println("\noutput n° " + outputcounter++ + ": " + result + "...............................................");


        output.add(result);
        instructionPointer += 2;
    }

    /**
     * jump if true
     */
    private void opCode5() {
        Long parameter1 = getValueOfParameter(instructionPointer, 1);
        Long parameter2 = getValueOfParameter(instructionPointer, 2);

        if (!parameter1.equals(0L)) {
            instructionPointer = parameter2.intValue();
            //System.out.println("\nOPT5: New value instructionPointer: " + parameter2 + " max value is: " + memory.size());
        } else {
            instructionPointer += 3;
            //System.out.println("\nOPT5: instructionPointer +3 (normal) ");
        }
    }

    /**
     * jump if false
     */
    private void opCode6() {
        Long parameter1 = getValueOfParameter(instructionPointer, 1);
        Long parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1.equals(0L)) {
            instructionPointer = parameter2.intValue();
            //System.out.println("\nNew value instructionPointer: " + parameter2 + " max value is: " + memory.size());
        } else {
            instructionPointer += 3;
        }
    }

    /**
     * less than
     */
    private void opCode7() {
        long parameter1 = getValueOfParameter(instructionPointer, 1);
        long parameter2 = getValueOfParameter(instructionPointer, 2);

        if (parameter1 < parameter2) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    /**
     * equals
     */
    private void opCode8() {
        Long parameter1 = getValueOfParameter(instructionPointer, 1);
        Long parameter2 = getValueOfParameter(instructionPointer, 2);

        System.out.println("---------------------------------");
        System.out.println("parameter1: " + parameter1);
        System.out.println("parameter2: " + parameter2);
        System.out.println("---------------------------------");


        if (parameter1.equals(parameter2)) {
            writeResult(instructionPointer, 3, 1);
        } else {
            writeResult(instructionPointer, 3, 0);
        }
        instructionPointer += 4;
    }

    /**
     * adjust relative base
     */
    private void opCode9() {

        long parameter1 = getValueOfParameter(instructionPointer, 1); //debug ... is dit immediatMode, of hangt het er ook vanaf
        //System.out.print("\n     relative base: " + relativeBase + " -> " + (relativeBase + parameter1));
        relativeBase += parameter1;
        instructionPointer += 2;
    }

    private Long getValueOfParameter(int instructionPointer, int parameterNr) {
        int positionMode = getPositionModeOfParameter(instructionPointer, parameterNr);
        System.out.print("\n     positionMode is: " + positionMode);

        int indexOfParameter = assurePosition(instructionPointer + parameterNr);
        int indexToRead;

        switch (positionMode){
            case 0:
                indexToRead = assurePosition(memory.get(indexOfParameter).intValue());
                return memory.get(indexToRead);
            case 1:
                return memory.get(indexOfParameter);
            case 2:
                indexToRead = assurePosition(memory.get(indexOfParameter).intValue() + relativeBase);
                //System.out.print("\n     indexToRead is: " + indexToRead);
                return memory.get(indexToRead);
            default: throw new RuntimeException("PositionMode: " + positionMode + "does not exist");
        }
    }

    private Long getValueOfParameterImmediateMode(int instructionPointer, int parameterNr) {
        return memory.get(assurePosition(instructionPointer + parameterNr));
    }

    private int getPositionModeOfParameter(int instructionPointer, int parameterNr) {
        int instructionCode = memory.get(instructionPointer).intValue();
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);

/*    ik denk dat ik dit niet meer nodig heb, vermits we memorymode niet meer gebruiken

//only position mode is writen to the memorymode (till now)
        if (memoryMode.get(instructionPointer + parameterNr).equals(0)) {
            return 0;
            //todo I don't like this feature. I'd prefer just to override the parameter mode in the instructionValue (refactor)
        } else {
            return digits.get(3 - parameterNr);
        }*/

        return digits.get(3 - parameterNr);
    }

    private void setPositionModeOfParameter(int instructionPointer, int parameterNr, int positionMode) {
        int instructionCode = memory.get(instructionPointer).intValue();
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);
        digits.set((3 - parameterNr), positionMode);
        Integer newInstructionCode = Transformers.transformDigitListToInt(digits);
        memory.set(instructionPointer, newInstructionCode.longValue());
    }

    //Day 5: Parameters that an instruction writes to will never be in immediate mode.
    //todo ... dit snap ik niet. Ergens bij de intcomputer stond dat waarden die we schrijven, dat die een altijd position mode null moeten krijgen, dat die niet mogen overschreven worden. Pas als ik die feature afzet krijg ik de juiste oplossing
    private void writeResult(int instructionPointer, int parameterNr, long result) {

        int positionMode = getPositionModeOfParameter(instructionPointer, parameterNr);
        int indexOfParameter = assurePosition(instructionPointer + parameterNr);
        int indexToWrite;


        switch (positionMode) {
            case 0:
                indexToWrite = assurePosition(memory.get(indexOfParameter).intValue());
                break;
            case 1:
                indexToWrite = indexOfParameter;
                break;
            case 2:
                indexToWrite = assurePosition(memory.get(indexOfParameter).intValue() + relativeBase);
                break;
            default:
                throw new RuntimeException("PositionMode: " + positionMode + "does not exist");
        }
        memory.set(indexToWrite, result);

        if (getPositionModeOfParameter(instructionPointer, parameterNr) == 1) {
            setPositionModeOfParameter(instructionPointer, parameterNr, 0);
        }

        //  memoryMode.set((indexOfParameter), 0);

    }

    /**
     * gets the computerinput -> each algorithm gets a first and a following input
     * the first input is the amplifierPhaseSetting, the next is 0 or the output of the previous amplifier algorithm
     */
    public long getComputerInput() {

        while (input.isEmpty()) {
            try {
                Thread.sleep(1);
                //System.out.println("....................waiting for input");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long newInput = input.poll();
        System.out.println("\ninput n°" + inputcounter++ + ": " + newInput + ".............................................");
        return newInput;
    }

    private int getOpCode(int instructionPointer) {
        int instructionCode = memory.get(assurePosition(instructionPointer)).intValue();
        List<Integer> digits = Transformers.transformNumberToDigitList(instructionCode, 5);

        return Integer.parseInt(String.valueOf(digits.get(3)) + digits.get(4));
    }

    private int assurePosition(int position){
        int numberOfExtraPositions;
        if (position > memory.size()){
            numberOfExtraPositions = position + 5 - memory.size();
            List<Long> memoryExtension = Stream.generate(() -> 0L).limit(numberOfExtraPositions).collect(Collectors.toList());
            memory.addAll(memoryExtension);

            List<Integer> memoryModeExtension = Stream.generate(() -> 9).limit(numberOfExtraPositions).collect(Collectors.toList());
            memoryMode.addAll(memoryModeExtension);
        }
        return position;
    }
}
