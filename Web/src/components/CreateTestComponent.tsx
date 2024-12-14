import {
    ActionIcon, Box,
    Button,
    Divider,
    Group,
    Paper,
    PaperProps,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {Link} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";
import {DateInput} from "@mantine/dates";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";

dayjs.extend(customParseFormat);

export function CreateTestComponent(props: PaperProps) {
    const form = useForm({
        initialValues: {
            name: '',
            date_start: new Date,
            date_end: new Date,
            questions: []
        },
    });
    console.log(form.values.date_start.toString())
    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/teachers"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Тест
            </Text>

            <Divider label={'Создание'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <Group ml={'auto'} mr={'auto'}>
                        <TextInput
                            label="Название"
                            placeholder="Название Теста"
                            value={form.values.name}
                            onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                            radius="md"
                        />

                        <DateInput
                            label="Дата Начала"
                            className={"date"}
                            dateParser={(s) =>
                                dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate().getTime()
                                    ? dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate()
                                    : new Date(s)
                            }
                            value={form.values.date_start}
                            valueFormat="DD/MM/YYYY HH:mm:ss"
                            radius="md"
                        />

                        <DateInput
                            label="Дата Окончания"
                            className={"date"}
                            dateParser={(s) =>
                                dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate().getTime()
                                    ? dayjs(s, "DD/MM/YYYY HH:mm:ss").toDate()
                                    : new Date(s)
                            }
                            value={form.values.date_end}
                            valueFormat="DD/MM/YYYY HH:mm:ss"
                            radius="md"
                        />
                    </Group>
                </Stack>

                <Group justify="end" mt="xl">
                    <Button type="submit" radius="xl" >
                        Создать Тест
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}