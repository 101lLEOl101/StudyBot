import {
    ActionIcon, Box,
    Button, Checkbox,
    Divider,
    Group, Loader, Notification,
    Paper,
    Stack, Table,
    Text, TextInput,
} from '@mantine/core';
import {Link, useNavigate, useParams} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";
import {DateInput} from "@mantine/dates";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";
import {FieldArray, Formik, Form} from "formik";
import {axiosConfig} from "../../axios.ts";
import {useState} from "react";
import {useMutation, useQuery} from "@tanstack/react-query";
import {IconPlus} from "@tabler/icons-react";
import {fetchDiscipline} from "../api/service.ts";

dayjs.extend(customParseFormat);

interface TestForm {
    name_test: string;
    date_start: Date;
    date_end: Date;
    questions: {
        question_text: string;
        answers: {
            answer_text: string;
            correct: boolean;
        }[];
    }[];
}

export function CreateTestComponent() {
    const navigate = useNavigate();
    const { id } = useParams();
    const CreateTestFun = async (form:TestForm) => {
        console.log(form.name_test, form.questions);
        const body = {
            createTime: form.date_start.toISOString(),
            expiresTime: form.date_end.toISOString(),
            discipline: {
                id: Number(id),
                disciplineName: data.data.disciplineName,
            },
            testName: form.name_test,
            questions: form.questions.map((question) => ({
                questionText: question.question_text,
                questionType: "SINGLE_CHOICE",
                answers: question.answers.map((answer) => ({
                    isStudentAnswer: false,
                    correct: answer.correct.toString(),
                    answerText: answer.answer_text,
                })),
            })),
        };
        return (await axiosConfig.post('/api/test/create-by-tree', body)).data;
    }
    const [errorMessage, setErrorMessage] = useState("");
    const [loadingMessage, setLoadingMessage] = useState(false)

    const {mutate} = useMutation(CreateTestFun, {
        onSuccess: () => {
            navigate('/active-tests');
        },
        onError: () => {
            setErrorMessage("Ошибка создания");
        },
        onSettled: () => {
            setLoadingMessage(false);
        },
    });

    const { data, status, error} = useQuery(
        ['discipline', id],
        () => fetchDiscipline(Number(id)),
    );
    if(status === "loading") {
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Loader size="lg"/>
            </Box>
        )
    } else if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        )
    }
    const handleCreate = (form:TestForm) => {
        setLoadingMessage(true);
        setErrorMessage("");
        mutate(form);
    };
    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder >
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/disciplines"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Тест
            </Text>

            <Divider label={'Создание'} labelPosition="center" my="lg" />

            <Formik initialValues={{name_test: '', date_start: new Date, date_end: new Date, questions: [{question_text: '', answers: [{correct: false, answer_text: '',}]}]}} onSubmit={handleCreate}>{({values, handleChange, setFieldValue}) => (
                <Form>
                    <Stack>
                        <Group ml={'auto'} mr={'auto'}>
                            <TextInput
                                name = "name_test"
                                label="Название"
                                placeholder="Название Теста"
                                radius="md"
                                value={values.name_test}
                                onChange={handleChange}
                            />
                            <DateInput
                                label="Дата Начала"
                                className={"date"}
                                dateParser={(s) =>
                                    dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate().getTime()
                                        ? dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate()
                                        : new Date(s)
                                }
                                name="date_start"
                                value={values.date_start}
                                valueFormat="DD/MM/YYYY HH:mm:ss"
                                radius="md"
                            />
                            <DateInput
                                label="Дата Окончания"
                                className={"date"}
                                name="date_end"
                                dateParser={(s) =>
                                    dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate().getTime()
                                        ? dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate()
                                        : new Date(s)
                                }
                                value={values.date_end}
                                valueFormat="DD/MM/YYYY HH:mm:ss"
                                radius="md"
                            />
                        </Group>
                    </Stack>
                    <Table verticalSpacing="sm">
                        <Table.Thead>
                            <Table.Tr>
                                <Table.Th ta={"left"}>Вопросы</Table.Th>
                            </Table.Tr>
                        </Table.Thead>
                        <Table.Tbody>
                            <FieldArray name="questions" render={questionHelper => (
                                <>
                                    { values.questions.map((question, index) => (
                                        <Paper ml={'auto'} mr={'auto'} m={10} key={index} radius="md" p="xl" pt={"5"} withBorder>
                                            <Box display={"flex"} ml={"100%"} onClick={() => questionHelper.remove(index)}>
                                                <ActionIcon  radius={100} variant="subtle" color="red">
                                                    <IoCloseCircleOutline size={32}/>
                                                </ActionIcon>
                                            </Box>
                                            <Table.Tr>
                                                <TextInput
                                                    ml={"auto"}
                                                    mr={"auto"}
                                                    ta={"center"}
                                                    maw={400}
                                                    name = {`questions.${index}.question_text`}
                                                    label="Вопрос"
                                                    placeholder="Текст Вопроса"
                                                    radius="md"
                                                    value={values.questions[index].question_text}
                                                    onChange={handleChange}
                                                />
                                                <Table>
                                                    <Table.Thead>
                                                        <Table.Tr>
                                                            <Table.Th ta={"left"}>Ответы</Table.Th>
                                                        </Table.Tr>
                                                    </Table.Thead>
                                                    <Table.Tbody>
                                                        <FieldArray name={`questions.${index}.answers`} render={answerHelper => (
                                                            <>
                                                            <Group mb={10}>
                                                                {question.answers.map((answer,jndex) => (
                                                                    <Paper ml={"auto"} mr={"auto"} key={jndex} radius="md" p={"sm"} withBorder>
                                                                        <Box display={"flex"} ml={"92%"} onClick={() => answerHelper.remove(jndex)}>
                                                                            <ActionIcon  radius={100} variant="subtle" color="red">
                                                                                <IoCloseCircleOutline size={32}/>
                                                                            </ActionIcon>
                                                                        </Box>
                                                                        <Group ml={'auto'} mr={'auto'} m={10}>
                                                                            <TextInput
                                                                                name={`questions.${index}.answers.${jndex}.answer_text`}
                                                                                label="Ответ"
                                                                                placeholder="Ответ"
                                                                                radius="md"
                                                                                value={values.questions[index].answers[jndex].answer_text}
                                                                                onChange={handleChange}
                                                                            />
                                                                            <Checkbox pt={23}
                                                                                name={`answers.${jndex}.correct`}
                                                                                      checked={answer.correct}
                                                                                      onChange={(event) => {
                                                                                          const isChecked = event.currentTarget.checked;
                                                                                          questionHelper.replace(index, {
                                                                                              ...question,
                                                                                              answers: question.answers.map((a, aj) =>
                                                                                                  aj === jndex ? { ...a, correct: isChecked } : { ...a, correct: false }
                                                                                              ),
                                                                                          });
                                                                                      }}
                                                                            />
                                                                        </Group>
                                                                    </Paper>
                                                                ))}
                                                            </Group>
                                                                <Group justify="end">
                                                                <Button  onClick={() => answerHelper.push({correct: false, answer_text: '',})}>
                                                                    <IconPlus>
                                                                    </IconPlus>
                                                                </Button>
                                                                </Group>
                                                            </>
                                                        )}>
                                                        </FieldArray>
                                                    </Table.Tbody>
                                                </Table>
                                            </Table.Tr>
                                        </Paper>
                                    ))}
                                    <Button onClick={() => questionHelper.push({question_text: "",answers:[{correct: false, answer_text: '',}]})}>
                                        <IconPlus>
                                        </IconPlus>
                                    </Button>
                                </>
                            )}>
                            </FieldArray>
                        </Table.Tbody>
                    </Table>
                    {errorMessage && (
                        <Text color="red" size="sm" mt="sm">
                            {errorMessage}
                        </Text>
                    )}
                    {loadingMessage && (
                        <Box pt = {10} style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                            <Loader size="lg"/>
                        </Box>
                    )}
                    <Group justify="end" mt="xl">
                        <Button type="submit" radius="xl" >
                            Создать Тест
                        </Button>
                    </Group>
                </Form>)}
            </Formik>
        </Paper>
    );
}