import {
    ActionIcon, Box,
    Button,
    Divider,
    Group, Loader, NativeSelect, Notification,
    Paper,
    PaperProps,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import {useForm, UseFormReturnType} from '@mantine/form';
import {Link, useNavigate} from "react-router-dom";
import { IoCloseCircleOutline } from "react-icons/io5";
import {axiosConfig} from "../../axios.ts";
import {useState} from "react";
import {useMutation, useQuery} from "@tanstack/react-query";
import {fetchDisciplines} from "../api/service.ts";

export function CreateGroupComponent(props: PaperProps) {
    const navigate = useNavigate();
    const CreateDisciplineFun = async (form: UseFormReturnType<{ name: string; discipline: string }>) => {
        const body = {
            partyName: form.values.name,
            workers: [parseInt(localStorage.getItem("userId") || "0")],
            disciplines: [data.data.find((discipline) => discipline.disciplineName === form.values.discipline).id]
        };
        console.log(form.values.name, [parseInt(localStorage.getItem("userId") || "0")], [data.data.find((discipline) => discipline.disciplineName === form.values.discipline).id], )
        return (await axiosConfig.post('/api/party/create', body)).data;
    }
    const [errorMessage, setErrorMessage] = useState("");
    const [loadingMessage, setLoadingMessage] = useState(false);
    const {mutate} = useMutation(CreateDisciplineFun, {
        onSuccess: () => {
            navigate('/group-students');
        },
        onError: () => {
            setErrorMessage("Ошибка создания");
        },
        onSettled: () => {
            setLoadingMessage(false);
        },
    });
    const form = useForm({
        initialValues: {
            name: '',
            discipline: '',
        },
    });
    const {status, data, error } = useQuery(["disciplines"], fetchDisciplines);
    if(status === "loading"){
        return (
            <Box style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%'}}>
                <Loader size="lg"/>
            </Box>
        )
    }else if (status === "error") {
        return (
            <Notification color="red" title="Error loading">
                {error.message || 'An unknown error occurred.'}
            </Notification>
        )
    }
    const disciplines = data.data.map((discipline) => {
        return discipline.disciplineName
    });
    const handleCreate = () => {
        setLoadingMessage(true);
        setErrorMessage("");
        mutate(form);
    };
    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/group-students"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Группа
            </Text>

            <Divider label={'Создание'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Название"
                        placeholder="Название Группы"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        radius="md"
                    />
                    <NativeSelect
                        radius="md"
                        data={disciplines}
                        label="Группа"
                        value={form.values.discipline}
                        onChange={(event) => form.setFieldValue('discipline', event.currentTarget.value)}
                    />
                </Stack>
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
                    <Button onClick={handleCreate} type="submit" radius="xl" >
                        Добавить Группу
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}