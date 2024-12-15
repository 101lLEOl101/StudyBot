import {
    ActionIcon, Box,
    Button,
    Divider,
    Group, Loader,
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
import {useMutation} from "@tanstack/react-query";

export function CreateDisciplineComponent(props: PaperProps) {
    const navigate = useNavigate();
    const form = useForm({
        initialValues: {
            name: '',
        },
    });
    const CreateTeacherFun = async (form: UseFormReturnType<{ name: string }>) => {
        const body = {
            disciplineName: form.values.name,
        };
        return (await axiosConfig.post('/api/discipline/create', body)).data;
    }
    const [errorMessage, setErrorMessage] = useState("");
    const [loadingMessage, setLoadingMessage] = useState(false)

    const {mutate} = useMutation(CreateTeacherFun, {
        onSuccess: () => {
            navigate('/disciplines');
        },
        onError: () => {
            setErrorMessage("Ошибка создания");
        },
        onSettled: () => {
            setLoadingMessage(false);
        },
    });

    const handleCreate = () => {
        setLoadingMessage(true);
        setErrorMessage("");
        mutate(form);
    };
    return (
        <Paper radius="md" p="xl" pt={"5"} withBorder {...props}>
            <Box display={"flex"} ml={"100%"}>
                <Link to={"/disciplines"}>
                    <ActionIcon  radius={100} variant="subtle" color="red">
                        <IoCloseCircleOutline size={32}/>
                    </ActionIcon>
                </Link>
            </Box>
            <Text size="lg" ta={"center"} fw={500}>
                Дисциплина
            </Text>

            <Divider label={'Создание'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Название"
                        placeholder="Название Дисциплины"
                        value={form.values.name}
                        onChange={(event) => form.setFieldValue('name', event.currentTarget.value)}
                        radius="md"
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
                        Создать Дисциплину
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}