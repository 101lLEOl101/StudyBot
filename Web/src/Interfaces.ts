export interface Worker{
    id: number,
    firstName: string,
    lastName: string,
    nickName: string,
    password: string,
    workerRole: string,
    partys: number[],
}

export interface ActiveTest {
    id: number;
    testName: string;
    disciplineName: string;
    expiresTime: string;
}

export interface CreateWorker{
    firstName: string,
    lastName: string,
    nickName: string,
    password: string,
}

export interface Discipline{
    id: number,
    disciplineName: string,
    tests: number[],
    partys: number[],
}

export interface Party{
    id: number,
    studentsNum: number,
    partyName: string,
    workers: number[],
    disciplines: number[],
    subs: number[],
}

export interface Test{
    id: number,
    createTime: string,
    expiresTime: string,
    discipline: number,
    disciplineName: string,
    testName: string,
    questions: number[],
    results: number[],
    isExpired: boolean,
}

export interface TestInfo{
    id: number,
    name: string,
    activeTests: Test[],
    students: string[],
}

export interface Question{
    id: number,
    type: number,
    text: string,
}

export interface AnswerOption{
    id: number,
    correct: number,
    question: number,
    text: string,
}

export interface StudentAnswer{
    id: number,
    percentage: number,
    question: number,
    result: number,
}

export interface Result{
    id: number,
    finishTime: string,
    startTime: string,
    student: number,
    test: number,
}

